import requests
from bs4 import BeautifulSoup

# basUrl = "https://www.amazon.com/dp/0307142477"
basUrl = 'https://www.amazon.com/errors/validateCaptcha'
# basUrl = 'https://www.amazon.com/errors/validateCaptcha?amzn=Y6uuJZhU1sIf8Dog%2F8sfuw%3D%3D&amzn-r=%2F&field-keywords=XFXLHF'

head = {
    'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.8 Safari/537.36'
}

uaList = {
    # 'user-agent': 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)',
    # 'user-agent': 'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
    # 'user-agent': 'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
    # 'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36',
    # 'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.8 Safari/537.36',
    # 'user-agent': 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)'
    # 'user-agent': 'Opera/9.80(Macintosh;IntelMacOSX10.6.8;U;en)Presto/2.8.131Version/11.11'
    # 'user-agent': 'Opera/9.80(WindowsNT6.1;U;en)Presto/2.8.131Version/11.11'
    # 'user-agent': 'Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11',
}

cookies = {
    'cookie': "session-id=130-5263238-8989869; i18n-prefs=USD; ubid-main=134-7708903-3770659; lc-main=en_US; av-timezone=Asia/Shanghai; sp-cdn=\"L5Z9:CN\"; s_vnum=2070063665728%26vn%3D1; s_nr=1638063675608-New; s_dslv=1638063675610; session-id-time=2082787201l; session-token=iSb81HNsBTNXihUU8hq2qGmLnYqbAje+4iYE39IVYXBCGAXN2Lz2p9r7Xz9U2GpWJAh/heuv/M6ziqWy+aJB8qs4o+UMh3wBWZU2nI4Kv+iXU+Cfgy3pUSXQG2XS8i1fhiabmhvQGCn+n6NDKb7qWs6XYilw295s0o8IfvXS6pyVx2eBD5WqjV4eQFRYSnyh; csm-hit=adb:adblk_no&t:1638700160461&tb:7E6QVYBYTSTQ5ADQ6YZ6+s-7E6QVYBYTSTQ5ADQ6YZ6|1638700160461"
    # "cookie": "session-id=130-5263238-8989869; i18n-prefs=USD; ubid-main=134-7708903-3770659; lc-main=en_US; av-timezone=Asia/Shanghai; sp-cdn=\"L5Z9:CN\"; s_vnum=2070063665728%26vn%3D1; s_nr=1638063675608-New; s_dslv=1638063675610; session-id-time=2082787201l; session-token=Y8ZU1eWCQy9rbHbN96+wCh/z4l51JSuk8eBlAvRv9G7g7WIO/P0KCy7JjC4YwQOqEyx5z7sG910gx3nQZda1ukxEBTKlrm6Io2ace/NPMzb7Bbv3UdgfQSKejcQKN/bAhSdvYF6Rf1dPg+a0O9rQoExWGPlnVsMfivnIXGGVkz/GDtReZOfuLsoMBBDu2F2J; csm-hit=adb:adblk_no&t:1638770113544&tb:7BMRJ54PKRBP6B7XZFQ6+s-7BMRJ54PKRBP6B7XZFQ6|1638770113544"
    # "cookie": "session-id=130-5263238-8989869; i18n-prefs=USD; ubid-main=134-7708903-3770659; lc-main=en_US; av-timezone=Asia/Shanghai; sp-cdn=\"L5Z9:CN\"; s_vnum=2070063665728%26vn%3D1; s_nr=1638063675608-New; s_dslv=1638063675610; session-id-time=2082787201l; session-token=Y8ZU1eWCQy9rbHbN96+wCh/z4l51JSuk8eBlAvRv9G7g7WIO/P0KCy7JjC4YwQOqEyx5z7sG910gx3nQZda1ukxEBTKlrm6Io2ace/NPMzb7Bbv3UdgfQSKejcQKN/bAhSdvYF6Rf1dPg+a0O9rQoExWGPlnVsMfivnIXGGVkz/GDtReZOfuLsoMBBDu2F2J; csm-hit=adb:adblk_no&t:1638770605213&tb:7BMRJ54PKRBP6B7XZFQ6+s-7BMRJ54PKRBP6B7XZFQ6|1638770605213"
    # 'cookie': "session-id=130-5263238-8989869; i18n-prefs=USD; ubid-main=134-7708903-3770659; lc-main=en_US; av-timezone=Asia/Shanghai; sp-cdn=\"L5Z9:CN\"; s_vnum=2070063665728%26vn%3D1; s_nr=1638063675608-New; s_dslv=1638063675610; session-id-time=2082787201l; session-token=iSb81HNsBTNXihUU8hq2qGmLnYqbAje+4iYE39IVYXBCGAXN2Lz2p9r7Xz9U2GpWJAh/heuv/M6ziqWy+aJB8qs4o+UMh3wBWZU2nI4Kv+iXU+Cfgy3pUSXQG2XS8i1fhiabmhvQGCn+n6NDKb7qWs6XYilw295s0o8IfvXS6pyVx2eBD5WqjV4eQFRYSnyh; csm-hit=adb:adblk_no&t:1638700160461&tb:7E6QVYBYTSTQ5ADQ6YZ6+s-7E6QVYBYTSTQ5ADQ6YZ6|1638700160461"

}

proxy = {
    'http': '218.75.96.162:3128'
}

params = {
    'amzn': 'mSvWAy6pDy8NhGqQoVJTRw==',
    'amzn-r': '/dp/0307142477',
    'field-keywords': 'TKACXG'
}

# response = requests.get(basUrl, headers=head, proxies=proxy, params=params)
response = requests.get(basUrl, headers=head, proxies=proxy)


with open('1.html', 'w', encoding='utf-8') as fp:
    fp.write(response.text)
