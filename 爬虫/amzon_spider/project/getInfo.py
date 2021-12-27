import json
import re


# 通过re正则表达式解析数据

def getMovieInfo(ASIN, html):
    movie_info = {
        'ASIN': ASIN,
        'name': "",
        'director': "",
        'actor': "",
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
    # print(name[0].strip())

    # director
    director_pattern = re.compile(
        r'<li><span class="a-list-item">.*?<span class="a-text-bold">Director.*?</span>.*?'
        r'<span>(?P<item>.*?)</span>.*?</span></li>',
        re.S)
    director = director_pattern.findall(html)
    if director.__len__() > 0:
        movie_info.__setitem__('director', director[0].strip())
    # print(director[0].strip())

    # actor
    actor_Pattern = re.compile(
        r'<li><span class="a-list-item">.*?<span class="a-text-bold">Actors.*?</span>'
        r'.*?<span>(?P<item>.*?)</span>.*?</span></li>',
        re.S)
    actor = actor_Pattern.findall(html)
    if actor.__len__() > 0:
        movie_info.__setitem__('actor', actor[0].strip())
    # print(actor[0].strip())

    # date
    date_pattern1 = re.compile(
        r'<li><span class="a-list-item">.*?<span class="a-text-bold">Date First Available.*?</span>'
        r'.*?<span>(?P<item>.*?)</span>.*?</span></li>',
        re.S)
    date_pattern2 = re.compile(
        r'<li><span class="a-list-item">.*?<span class="a-text-bold">Release date.*?</span>'
        r'.*?<span>(?P<item>.*?)</span>.*?</span></li>',
        re.S)
    date = date_pattern1.findall(html)
    if date.__len__() <= 0:
        date = date_pattern2.findall(html)
        if date.__len__() > 0:
            movie_info.__setitem__('date', date[0].strip())
    else:
        movie_info.__setitem__('date', date[0].strip())

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

    # reviewNum
    reviewNum_pattern = re.compile(
        '<span id="acrCustomerReviewText" class="a-size-base">(?P<totalscore>.*?)</span>',
        re.S)
    reviewNum = reviewNum_pattern.findall(html)
    if reviewNum.__len__() > 0:
        movie_info.__setitem__('reviewNum', reviewNum[0].strip())

    return movie_info


def getReviewInfo(ASIN, html):
    reviewInfoList = []

    # 第一条评论的正则表达式
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

    # 其他评论的正则表达式
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
    # reviews_info = getReviewInfo('ASIN', html)
    with open('../test/movie.json', 'a+', encoding='utf-8') as f1:
        f1.write(json.dumps(movie_info) + '\n')
    # with open('../test/review.json', 'a+', encoding='utf-8') as f2:
    #     for review in reviews_info:
    #         f2.write(json.dumps(review) + '\n')
    print("over")
