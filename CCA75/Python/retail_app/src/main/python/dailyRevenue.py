import sys

def dailyRevenue(path, orderID):
  orderItemsFile = open(path)
  orderItemsRead = orderItemsFile.read()
  orderItems = orderItemsRead.splitlines()
  orderItemsFilter = filter(lambda rec: int(rec.split(",")[1]) ==orderID, orderItems)
  orderItemsMap = map(lambda rec: float(rec.split(",")[4]), orderItemsFilter)
  orderItemsRevenue = reduce(lambda total, element: total+element ,orderItemsMap)
  return orderItemsRevenue

path = sys.argv[1]
orderID = int(sys.argv[2])

print(dailyRevenue(path, orderID))

