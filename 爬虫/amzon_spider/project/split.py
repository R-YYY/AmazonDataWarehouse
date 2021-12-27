# 用于用于拆分asin,容易被识别为爬虫,返回空字段,所以分割得小,多次爬取
with open('asin.txt', encoding='utf-8') as f:
    line = f.readline()
    index = 1
    while line != '':
        with open('../asin/' + str(index) + '.txt', 'w+', encoding='utf-8') as hh:
            for i in range(5000):
                if line != '':
                    hh.write(line)
                    line = f.readline()
        hh.close()
        index += 1
