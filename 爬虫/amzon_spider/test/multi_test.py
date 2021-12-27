import random
import threading
import time
from queue import Queue
from threading import Thread

import requests
from amazoncaptcha import AmazonCaptcha
from bs4 import BeautifulSoup

# +ASIN 基本爬取网站
base_url = "https://www.amazon.com/dp/"

# 输入验证码获取页面的api 网址 get请求，需要参数params
error_url = "https://www.amazon.com/errors/validateCaptcha"

# # 参数格式
# params = {
#     'amzn': '',  # 验证码网页源码中
#     'amzn-r': '',  # 目标相对https://www.amazon.com的路径
#     'field-keywords': ''  # 验证码
# }

# 请求头user-agent
uaList = [
    'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)',
    'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
    'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36',
    'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.8 Safari/537.36',
    'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)',
    'Opera/9.80(Macintosh;IntelMacOSX10.6.8;U;en)Presto/2.8.131Version/11.11',
    'Opera/9.80(WindowsNT6.1;U;en)Presto/2.8.131Version/11.11',
    'Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11',
]


# 随机获取一个代理ip
def get_proxy():
    return requests.get("http://127.0.0.1:5000/get/?type=http").json()


# 删除代理池中ip
def delete_proxy(proxy):
    requests.get("http://127.0.0.1:5000/delete/?proxy={}".format(proxy))


def is_error(html):
    soup = BeautifulSoup(html, "lxml")
    if soup.find(text="Enter the characters you see below") is None:
        return False
    else:
        return True


# 破解验证码,获取参数
def get_params(html):
    print("start find code!")
    soup = BeautifulSoup(html, "lxml")
    amzn = soup.find(name='input', attrs={'type': 'hidden', 'name': 'amzn'})['value']
    amzn_r = soup.find(name='input', attrs={'type': 'hidden', 'name': 'amzn-r'})['value']
    img_url = soup.find(name='div', attrs={'class': 'a-row a-text-center'}).find(name='img')['src']
    captcha = AmazonCaptcha.fromlink(img_url)
    code = captcha.solve()
    # 输入验证码获取页面的api 参数
    params = {
        'amzn': amzn,
        'amzn-r': amzn_r,
        'field-keywords': code
    }
    return params


# 爬虫代码
def spider(url):
    # 使用代理ip访问
    proxy = get_proxy().get("proxy")
    # 请求头
    header = {'user-agent': random.choice(uaList)}

    # 加上输验证码最多尝试5次
    max_count = 5
    while max_count > 0:
        try:
            response = requests.get(url, headers=header, proxies={"http": "http://{}".format(proxy)})
            while is_error(response.text) and max_count > 0:
                # 获取验证码及参数
                params = get_params(response.text)
                # 发送验证码请求
                response = requests.get(error_url, headers=header, params=params,
                                        proxies={"http": "http://{}".format(proxy)})
                max_count -= 1
            return response.text
        except Exception:
            print('error ' + str(6 - max_count))
            max_count -= 1
    # 删除代理池中代理
    delete_proxy(proxy)
    return None


# 保存文件
def save(ASIN, html):
    soup = BeautifulSoup(html, "lxml")
    # ASIN不存在
    if soup.find(name='img', attrs={
        'alt': "Sorry! We couldn't find that page. Try searching or go to Amazon's home page."}) is not None:
        print("no such movie!")
    # 验证码填写失败
    elif is_error(html):
        print("write code failed!")
    # 爬取成功
    else:
        with open(ASIN + '.html', 'w', encoding='utf-8') as fp:
            fp.write(html)
        print("finish write!")


def run(in_q, out_q):
    while in_q.empty() is not True:
        ASIN = in_q.get()
        url = base_url + ASIN
        print(url)
        html = spider(url)
        if html is not None:
            save(ASIN, html)
        else:
            print("some python code error occurred!")
        out_q.put(str(threading.current_thread().getName()))
        in_q.task_done()


if __name__ == '__main__':
    start_time = time.time()
    asin_queue = Queue()
    result_queue = Queue()
    # 读入所有asin，存入队列
    f = open('asin.txt', encoding='utf-8')
    for line in f:
        asin_queue.put(line.strip())
    f.close()

    # 开始大小
    print('queue 开始大小 %d' % asin_queue.qsize())

    # 8个线程
    for index in range(8):
        thread = Thread(target=run, args=(asin_queue, result_queue,))
        thread.daemon = True  # 随主线程退出而退出
        thread.start()

    asin_queue.join()  # 队列消费完 线程结束
    end_time = time.time()
    print('总耗时：%s' % (end_time - start_time))
    print('queue 结束大小 %d' % asin_queue.qsize())
    print('result_queue 结束大小 %d' % result_queue.qsize())

