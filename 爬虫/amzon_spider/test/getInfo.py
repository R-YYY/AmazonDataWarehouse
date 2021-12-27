import json
import re
from bs4 import BeautifulSoup


# 通过re正则表达式和bs4解析数据

def getMovieInfo(ASIN, html):
    movie_info = {
        'ASIN': ASIN,
        'name': "",
        'director': "",
        'actor': "",
        'writer': "",
        'date': "",
        'format': "",
        'type': "",
        'score': "",
        'reviewNum': ""
    }

    # name
    name_pattern = re.compile(
        r'<span id="productTitle" class="a-size-large product-title-word-break">(?P<item>.*?)</span>',
        re.S)
    name = name_pattern.findall(html)
    if name.__len__() > 0:
        movie_info.__setitem__('name', name[0].strip())

    soup = BeautifulSoup(html, "lxml")
    info = soup.find(name='div', attrs={'id': 'detailBulletsWrapper_feature_div'}).find(name='div', attrs={'id': 'detailBullets_feature_div'})

    # director
    tmp = info.find(name='span', text=re.compile("Director"))
    if tmp is not None:
        movie_info.__setitem__('director', tmp.find_next_sibling().string.strip())

    # actor
    tmp = info.find(name='span', text=re.compile("Actors"))
    if tmp is not None:
        movie_info.__setitem__('actor', tmp.find_next_sibling().string.strip())

    # writer
    tmp = info.find(name='span', text=re.compile("Writers"))
    if tmp is not None:
        movie_info.__setitem__('writer', tmp.find_next_sibling().string.strip())

    # date
    tmp = info.find(name='span', text=re.compile("Release date"))
    if tmp is not None:
        movie_info.__setitem__('date', tmp.find_next_sibling().string.strip())
    else:
        tmp = soup.find(name='span', text=re.compile("Date First Available"))
        if tmp is not None:
            movie_info.__setitem__('date', tmp.find_next_sibling().string.strip())

    # reviewNum
    tmp = soup.find(name='span', attrs={'id': 'acrCustomerReviewText'})
    if tmp is not None:
        movie_info.__setitem__('reviewNum', tmp.string.strip())

    # format
    format_pattern = re.compile(
        r'<span class="a-color-secondary">Format.*?</span>.*?<span>(?P<item>.*?)</span>',
        re.S)
    format = format_pattern.findall(html)
    if format.__len__() > 0:
        movie_info.__setitem__('format', format[0].strip())

    # type
    type_pattern = re.compile('<a class="a-link-normal a-color-tertiary".*?>(?P<category>.*?)</a>', re.S)
    type = type_pattern.findall(html)
    if type.__len__() > 0:
        movie_info.__setitem__('type', type[-1].strip())

    # score
    score_pattern = re.compile(
        '<span data-hook="rating-out-of-text" class="a-size-medium a-color-base">(?P<totalscore>.*?)</span>',
        re.S)
    score = score_pattern.findall(html)
    if score.__len__() > 0:
        movie_info.__setitem__('score', score[0].strip())

    return movie_info


def getReviewInfo(ASIN, html):
    reviewInfoList = []
    first_pattern = re.compile(r'review-views celwidget"><div id="(?P<id>.*?)" data-hook="review" '
                               r'class="a-section review aok-relative">.*?'
                               r'<span class="a-profile-name">(?P<name>.*?)</span>.*?<span class="a-icon-alt">'
                               r'(?P<score>.*?)</span>.*?href.*?<span>(?P<summary>.*?)</span>.*?'
                               r'<span data-hook="review-date"'
                               r' class="a-size-base a-color-secondary review-date">(?P<date>.*?)</span>.*?a-size-base '
                               r'review-text.*?<span>(?P<text>.*?)</span>.*?</div>', re.S)
    reviews = first_pattern.finditer(html)
    for review in reviews:
        reviewInfoList.append({
            "ASIN": ASIN,
            "reviewerID": review.group('id'),
            "userName": review.group('name'),
            "date": review.group('date'),
            "score": review.group('score'),
            "text": review.group('text').strip().replace('<br />', ' '),
            "summary": review.group('summary')
        })
    other_pattern = re.compile(r'<span class="cr-footer-line-height">.*?<div id="(?P<id>.*?)" '
                               r'data-hook="review" class="a-section review aok-relative">.*?'
                               r'<span class="a-profile-name">(?P<name>.*?)</span>.*?<span class="a-icon-alt">'
                               r'(?P<score>.*?)</span>.*?href.*?<span>(?P<summary>.*?)</span>.*?'
                               r'<span data-hook="review-date"'
                               r' class="a-size-base a-color-secondary review-date">(?P<date>.*?)</span>.*?a-size-base '
                               r'review-text.*?<span>(?P<text>.*?)</span>.*?</div>', re.S)
    reviews = other_pattern.finditer(html)
    for review in reviews:
        reviewInfoList.append({
            "ASIN": ASIN,
            "reviewerID": review.group('id'),
            "userName": review.group('name'),
            "date": review.group('date'),
            "score": review.group('score'),
            "text": review.group('text').strip().replace('<br />', ' '),
            "summary": review.group('summary')
        })
    return reviewInfoList


if __name__ == '__main__':
    with open('../test/B000CEVCD8.html', encoding='utf-8') as f:
        html = f.read()
        f.close()
    print("start")
    movie_info = getMovieInfo('ASIN', html)
    with open('movie.json', 'a+', encoding='utf-8') as f1:
        f1.write(json.dumps(movie_info) + '\n')
    print("over")
