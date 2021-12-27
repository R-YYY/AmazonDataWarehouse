import requests
from adodbapi import connect
from bs4 import BeautifulSoup
import re
from concurrent.futures import ProcessPoolExecutor
# import pymssql
import time
import random


class BasicInfo:
    asin = ""
    name = ""
    director = ""
    date = ""
    actor = ""
    score = ""
    type = ""


class Review:
    username = ""
    score = ""
    date = ""
    summary = ""
    text = ""


with open("IP", mode='r') as file:
    IPlist = file.readlines()
file.close()

cookiepool = [
    "session-id=145-2822684-1592058; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=L5Z9:CN; csm-hit=tb:s-2RCH4F2DF1DDPDK2PXE0|1638283657856&t:1638283657908&adb:adblk_no; ubid-main=133-5998542-0952623; session-token=F1rO2Mndkvf6fB5n33UpJYaKrGHCQujb49YQ/pL9ZuNyLNGmL95INPIBlQnyed1OZdZ25aZLWp9oGgnLzmuChxSqdl/m11BH60G1rR8JTgtecUd+7JYnAlm3h4htJYtI4R7oOWsD9KeydYBtdrXm/Fzu6FaKczrn/0aSQdU+rUjU4NfZs6haF7/4X+P1dD1b; lc-main=en_US; x-amz-captcha-1=1638281178712996; x-amz-captcha-2=+tXjz+bjbrdSlawzbLKYGQ==; skin=noskin",
    "session-id=145-2822684-1592058; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=L5Z9:CN; ubid-main=133-5998542-0952623; session-token=F1rO2Mndkvf6fB5n33UpJYaKrGHCQujb49YQ/pL9ZuNyLNGmL95INPIBlQnyed1OZdZ25aZLWp9oGgnLzmuChxSqdl/m11BH60G1rR8JTgtecUd+7JYnAlm3h4htJYtI4R7oOWsD9KeydYBtdrXm/Fzu6FaKczrn/0aSQdU+rUjU4NfZs6haF7/4X+P1dD1b; lc-main=en_US; skin=noskin",
    "session-id=145-2822684-1592058; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=L5Z9:CN; csm-hit=tb:WZXA0R8EEQXHFA8H1R0K+s-K9BZB0SR0G60062P4NNV|1638284334991&t:1638284334991&adb:adblk_no; ubid-main=133-5998542-0952623; session-token=F1rO2Mndkvf6fB5n33UpJYaKrGHCQujb49YQ/pL9ZuNyLNGmL95INPIBlQnyed1OZdZ25aZLWp9oGgnLzmuChxSqdl/m11BH60G1rR8JTgtecUd+7JYnAlm3h4htJYtI4R7oOWsD9KeydYBtdrXm/Fzu6FaKczrn/0aSQdU+rUjU4NfZs6haF7/4X+P1dD1b; lc-main=en_US; x-amz-captcha-1=1638281178712996; x-amz-captcha-2=+tXjz+bjbrdSlawzbLKYGQ==; skin=noskin",
    "session-id=145-2822684-1592058; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=L5Z9:CN; csm-hit=tb:WZXA0R8EEQXHFA8H1R0K+b-WZXA0R8EEQXHFA8H1R0K|1638273931658&t:1638273931658&adb:adblk_no; ubid-main=133-5998542-0952623; session-token=/FcS8u7CL3TLQftvHyOM8/rOjRfQUDxw9lOx1y5CU4bUem4FoVwbxDNwANM34Ym0LXWMiw/bamP9DA8J+qDwf3p2I0G4Xmkm1DGnZEnOTInGFxfnGmu2vrJ0JE6QI98aNjWZob9aCC1wCX0dU0ltl2aUOjxdj1jwSTPhRZClqc+/ojsTvxYbNYAMW6ixoUSi; lc-main=en_US; x-amz-captcha-1=1638281178712996; x-amz-captcha-2=+tXjz+bjbrdSlawzbLKYGQ=="
]

url = "https://www.amazon.com/dp/"
headers = {
    'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) Gecko/20100101 Firefox/94.0",
    'Cookie': "session-id=145-2822684-1592058; session-id-time=2082787201l; i18n-prefs=USD; sp-cdn=L5Z9:CN; csm-hit=tb:WZXA0R8EEQXHFA8H1R0K+b-WZXA0R8EEQXHFA8H1R0K|1638273931658&t:1638273931658&adb:adblk_no; ubid-main=133-5998542-0952623; session-token=/FcS8u7CL3TLQftvHyOM8/rOjRfQUDxw9lOx1y5CU4bUem4FoVwbxDNwANM34Ym0LXWMiw/bamP9DA8J+qDwf3p2I0G4Xmkm1DGnZEnOTInGFxfnGmu2vrJ0JE6QI98aNjWZob9aCC1wCX0dU0ltl2aUOjxdj1jwSTPhRZClqc+/ojsTvxYbNYAMW6ixoUSi; lc-main=en_US; x-amz-captcha-1=1638281178712996; x-amz-captcha-2=+tXjz+bjbrdSlawzbLKYGQ=="
}


# connect = pymssql.connect('(local)', 'sa', 'Lyn020109', 'amazon_data')


def spider(r, info):
    url1 = url + r.replace('\n', '')
    info.asin = r.replace('\n', '')
    proxy = {
        "http": "http://" + random.choice(IPlist).replace('\n', '')
    }
    session = requests.session()
    resp = session.get(url1, headers=headers, proxies=proxy)
    if resp.status_code != 200:
        with open(r'爬取失败列表', mode='a') as w:
            w.writelines(r)
        w.close()
    else:
        page = BeautifulSoup(resp.text, "html.parser")
        name = page.findAll("span", class_="a-size-large product-title-word-break")
        if name.__len__() <= 0:
            with open(r'爬取失败列表', mode='a') as w:
                w.writelines(r)
            w.close()
        else:
            info.name = str(name[0].text).replace('\n', '').replace('\'', '’')
            page_content = resp.text
            obj1 = re.compile(
                r'<li><span class="a-list-item">.*?<span class="a-text-bold">Director.*?</span>.*?'
                r'<span>(?P<item>.*?)</span>.*?</span></li>',
                re.S)
            result1 = obj1.findall(page_content)
            if result1.__len__() > 0:
                info.director = result1[0]
            else:
                info.director = "无"
            obj2 = re.compile(
                r'<li><span class="a-list-item">.*?<span class="a-text-bold">Date First Available.*?</span>'
                r'.*?<span>(?P<item>.*?)</span>.*?</span></li>',
                re.S)
            result2 = obj2.findall(page_content)
            if result2.__len__() > 0:
                info.date = result2[0]
            else:
                info.date = "无"
            obj3 = re.compile(
                r'<li><span class="a-list-item">.*?<span class="a-text-bold">Actors.*?</span>'
                r'.*?<span>(?P<item>.*?)</span>.*?</span></li>',
                re.S)
            result3 = obj3.findall(page_content)
            if result3.__len__() > 0:
                info.actor = result3[0]
            else:
                info.actor = "无"
            obj4 = re.compile(r'<div id=".*?" data-hook="review" class="a-section review aok-relative">.*?'
                              r'<span class="a-profile-name">(?P<name>.*?)</span>.*?<span class="a-icon-alt">'
                              r'(?P<score>.*?)</span>.*?href.*?<span>(?P<summary>.*?)</span>.*?'
                              r'<span data-hook="review-date"'
                              r' class="a-size-base a-color-secondary review-date">(?P<date>.*?)</span>.*?a-size-base '
                              r'review-text.*?<span>(?P<text>.*?)</span>.*?</div>', re.S)
            result4 = obj4.finditer(page_content)
            for result in result4:
                if connect:
                    cursor = connect.cursor()
                    sql2 = "insert into dbo.review(asin, username, score, date, summary, text)values('" \
                           + r.replace('\n', '') + "','" \
                           + result.group("name").replace('\'', '‘') + "','" \
                           + result.group("score").replace('\'', '‘') + "','"\
                           + result.group("date").replace('\'', '‘') + "','" \
                           + result.group("summary").replace('\'', '‘') + "','" \
                           + result.group("text").replace('\'', '‘') + "')"
                    cursor.execute(sql2)
                    connect.commit()
                    cursor.close()
                # json_test.Tojson2(r.replace('\n', ''), result.group("name").replace('\'', '‘'),
                #                   result.group("score").replace('\'', '‘'), result.group("date").replace('\'', '‘'),
                #                   result.group("summary").replace('\'', '‘'),
                #                   result.group("text").replace('\'', '‘').replace('\n', ''))
            obj5 = re.compile('<a class="a-link-normal a-color-tertiary".*?>(?P<category>.*?)</a>', re.S)
            result5 = obj5.findall(page_content)
            if result5.__len__() > 2:
                info.type = result5[2].replace('\n', '').replace(' ', '')
            else:
                info.type = "无"
            obj6 = re.compile(
                '<span data-hook="rating-out-of-text" class="a-size-medium a-color-base">(?P<totalscore>.*?)</span>',
                re.S)
            result6 = obj6.findall(page_content)
            if result6.__len__() > 0:
                info.score = result6[0]
            else:
                info.score = "无"
            # json_test.Tojson1(info.asin, info.name, info.date, info.director, info.actor, info.score, info.type)
            if connect:
                sql1 = "insert into dbo.basic_info(asin, name, director, date, actor, score, type)values('" + info.asin + "', '" + info.name.replace(
                    '\'', '‘') + "', '" + info.director.replace('\'',
                                                                '‘') + "', '" + info.date + "', '" + info.actor + "','" + info.score + "','" + info.type.replace(
                    '\'', '‘') + "')"
                cursor = connect.cursor()
                cursor.execute(sql1)
                connect.commit()
                cursor.close()
            print(r.replace('\n', '') + "爬取成功")
    resp.close()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    start_time = time.time()
    with open(r'爬取列表', mode='r') as f:
        datalist = f.readlines()
    f.close()
    with ProcessPoolExecutor(20) as t:
        for element in datalist:
            basicinfo = BasicInfo
            t.submit(spider, element, basicinfo)
    end_time = time.time()
    print("time-cost", (end_time - start_time))
