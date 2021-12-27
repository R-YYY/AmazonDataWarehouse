from amazoncaptcha import AmazonCaptcha

# link = 获取到的验证码图片链接
# 测试示例
# link = 'https://images-na.ssl-images-amazon.com/captcha/usvmgloq/Captcha_kwrrnqwkph.jpg'
link = 'https://images-na.ssl-images-amazon.com/captcha/qmdddjhv/Captcha_nsrawkhyvc.jpg'

captcha = AmazonCaptcha.fromlink(link)
solution = captcha.solve()#识别后返回的结果，字符型

print(solution)
