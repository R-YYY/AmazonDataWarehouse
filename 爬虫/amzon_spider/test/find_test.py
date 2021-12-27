from amazoncaptcha import AmazonCaptcha
from bs4 import BeautifulSoup
import re


# soup = BeautifulSoup(open("amazon.html", encoding='utf-8'), "lxml")

# soup = BeautifulSoup(open("comment.html", encoding='utf-8'), "lxml")

# soup = BeautifulSoup(open("request_test.html", encoding='utf-8'), "lxml")

# # 电影信息，没有判断有无
# # 电影名
# print(soup.find(attrs={'id': 'productTitle'}).string.strip())
#
# # 导演
# print(soup.find(name='span', text=re.compile("Director")).find_next_sibling().string.strip().split(','))
#
# # 演员
# print(soup.find(name='span', text=re.compile("Actors")).find_next_sibling().string.strip().split(','))
#
# # ASIN
# print(soup.find(name='span', text=re.compile("ASIN")).find_next_sibling().string.strip().split(','))
#
# # 上映时间
# print(soup.find(name='span', text=re.compile("Release date")).find_next_sibling().string.strip().split(','))


# # 评论页面url
# print(soup.find(name='a', attrs={'data-hook': "see-all-reviews-link-foot"})['href'])

# 下一页评论的url
# print(soup.find(name='div', attrs={'id': 'cm_cr-pagination_bar'}).find(name='a')['href'])

# # 从一页的评论列表中解析数据
# for comment in soup.find(name='div', attrs={'id': 'cm_cr-review_list'}).find_all(attrs={'data-hook': 'review'}):
#     # 评论id
#     print(comment['id'])
#     # 评论用户name
#     print(comment.find(name='span', attrs={'class': 'a-profile-name'}).string.strip())
#     # 评论score
#     print(comment.find(name='i', attrs={'data-hook': 'review-star-rating'}).find(name='span').string.strip())
#     # 评论time
#     print(comment.find(name='span', attrs={'data-hook': 'review-date'}).string.strip())
#     # 评论summary
#     print("summary:", comment.find(name='a', attrs={'data-hook': 'review-title'}).find(name='span').string.strip())
#     # 评论text  长篇评论内有<br>，未判断
#     if comment.find(name='span', attrs={'data-hook': 'review-body'}).find(name='span').find(name='br') is None:
#         print("text:")
#         print(comment.find(name='span', attrs={'data-hook': 'review-body'}).find(name='span').string.strip())
#     else:
#         print("text:")
#         for item in comment.find(name='span', attrs={'data-hook': 'review-body'}).find(name='span').stripped_strings:
#             print(item)
#     # 评论helpfulness 没有判断有无
#     if comment.find(name='span', attrs={'data-hook': 'helpful-vote-statement'}) is not None:
#         print(comment.find(name='span', attrs={'data-hook': 'helpful-vote-statement'}).string.strip())
#     print("\n")
