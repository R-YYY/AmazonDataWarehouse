import csv


class Type:
    firstType = ""
    secondType = []


with open("type.csv") as f1:
    reader = csv.reader(f1)
    rows = [row for row in reader]
    with open("test.csv", "w", encoding="utf-8", newline="") as f2:
        write = csv.writer(f2)
        for row in rows:
            write.writerow(row[0].strip().split(":", 2))
        print("写入完毕！")
