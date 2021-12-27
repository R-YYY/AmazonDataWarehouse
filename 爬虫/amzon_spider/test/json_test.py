import json

data = {
    "ASIN": "1",
    "name": "2",
    "director": "3",
    "actor": "4",
    "date": "5",
    "format": "6",
    "type": "7",
    "score": "8"
}

data.__setitem__('ASIN', '123')
print(json.dumps(data))

review = dict(ASIN="1", reviewerID="2", userName="3", date="4", score="5", text="6", summary="7")


# with open("hh.json", 'a+') as f:
#     f.write(json.dumps(data) + '\n')
#     f.write(json.dumps(review) + '\n')
#     # json.dump(data, f)
#     # json.dump(review, f)
