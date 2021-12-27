import random
import time

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

cookie = {'session-id': '459-4568418-5692641', 'ubid-acbcn': '459-5049899-3055220',
          'x-wl-uid': '1AK7YMFc9IzusayDn2fT6Topjz3iAOpR3EeA2UQSqco8fo5PbK2aCpyBA/fdPMfKFqZRHc4IeyuU=',
          'session-token': 'OH1wPvfOj6Tylq2nnJcdn5wyxycR/lqyGsGU3+lUtU4mbC0ZD9s8/4Oihd1BlskUQG8zRbLVs9vfWXuiJmnRlDT4x35ircp2uLxOLNYQ4j5pzdFJIqqoZUnhHSJUq2yK80P3LqH8An7faXRCPW9BIqX1wu0WmHlSS9vYAPKA/2SGdV9b//EljYjIVCBjOuR/dKRiYEeGK3li0RJOVz7+vMWg7Rnzbx89QxlbCp0WyquZyVxG6f2mNw=="',
          'session-id-time': '2082787201l'}

c = {
    'i18n-prefs': 'USD',
    'session-id': '142-0132398-8301407',
    'session-id-time': '2082787201l',
    'sp-cdn': "L5Z9:CN",
    'ubid-main': '134-7942027-3479410',
    'session-token':'AJMFy8XRQJlnxoLEVie/HpS+HhhkPyWeeYG5/T9JXCLzgVJb/4FP97jblKInEvS8AKLDRRNaKQ0ZvXpqhoXulLy7T76Ptk0RqmuDjhEiHwfdDDlZlqZiJCOWmio79mjylQmWnRIC8BjPbZ1s9aKoqc7hYWjf9twIKUhL+/oT27UHmklFkXmw1JiwY/YNNwiU'
}

cookieList = [
{
    'i18n-prefs': 'USD',
    'session-id': '142-0132398-8301407',
    'session-id-time': '2082787201l',
    'sp-cdn': "L5Z9:CN",
    'ubid-main': '134-7942027-3479410',
    'session-token':'AJMFy8XRQJlnxoLEVie/HpS+HhhkPyWeeYG5/T9JXCLzgVJb/4FP97jblKInEvS8AKLDRRNaKQ0ZvXpqhoXulLy7T76Ptk0RqmuDjhEiHwfdDDlZlqZiJCOWmio79mjylQmWnRIC8BjPbZ1s9aKoqc7hYWjf9twIKUhL+/oT27UHmklFkXmw1JiwY/YNNwiU'
},
{
    'i18n-prefs': 'USD',
    'session-id': '132-2732843-4242344',
    'session-id-time': '2082787201l',
    'sp-cdn': "L5Z9:CN",
    # 'ubid-main': '134-7942027-3479410',
    # 'session-token':'AJMFy8XRQJlnxoLEVie/HpS+HhhkPyWeeYG5/T9JXCLzgVJb/4FP97jblKInEvS8AKLDRRNaKQ0ZvXpqhoXulLy7T76Ptk0RqmuDjhEiHwfdDDlZlqZiJCOWmio79mjylQmWnRIC8BjPbZ1s9aKoqc7hYWjf9twIKUhL+/oT27UHmklFkXmw1JiwY/YNNwiU'
},
]


# 随机获取一个代理ip
def get_proxy():
    return requests.get("http://127.0.0.1:5000/get/?type=http").json()


# 删除代理池中ip
def delete_proxy(proxy):
    requests.get("http://127.0.0.1:5000/delete/?proxy={}".format(proxy))


def is_code(html):
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
    header = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36'}
    # header = {'user-agent':"Mozilla/5.0 (Linux; Android 8.0; ANE-AL00 Build/HUAWEIANE-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044109 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060739) NetType/WIFI Language/zh_CN"}

    # 加上输验证码最多尝试5次
    max_count = 5
    while max_count > 0:
        try:
            response = requests.get(url, headers=header, proxies={"http": "http://{}".format(proxy)})
            while is_code(response.text) and max_count > 0:
                # 获取验证码及参数
                params = get_params(response.text)
                # 发送验证码请求
                response = requests.get(error_url, headers=header, params=params,
                                        proxies={"http": "http://{}".format(proxy)})
                max_count -= 1
            for item in response.cookies:
                print(item.name + ':' + item.value)
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
    if html is None:
        return
    # if soup.find(name='img', attrs={
    #     'alt': "Sorry! We couldn't find that page. Try searching or go to Amazon's home page."}) is not None:
    #     print("no such movie!")
    # # 验证码填写失败
    # elif is_error(html):
    #     print("write code failed!")
    # # 爬取成功
    else:
        with open('../test/' + ASIN + '111.html', 'w', encoding='utf-8') as fp:
            fp.write(html)
        fp.close()
    #     print("finish write!")


# 开始爬虫，并保存文件
def start(ASIN):
    url = base_url + ASIN
    print(url)
    html = spider(url)
    save(ASIN, html)


if __name__ == '__main__':
    start_time = time.time()
    # f = open('test.txt', encoding='utf-8')
    # asin_list = []
    # for line in f:
    #     asin_list.append(line.strip())
    # f.close()
    #
    # for asin in asin_list:
    #     start(asin)
    ASIN = "B000CEVCD8"
    start(ASIN)
    end_time = time.time()
    print('总耗时：%s' % (end_time - start_time))

    # 测试ASIN不存在
    # ASIN = '0615115187'
    # start(ASIN)
