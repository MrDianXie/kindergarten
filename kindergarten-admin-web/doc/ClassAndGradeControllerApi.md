
# 班级
## 获取班级列表
**URL:** `/admin/class/classList`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取班级列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-





**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/class/classList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 分页查询班级列表
**URL:** `/admin/class/list`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 分页查询班级列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
pages|int64|当前分页总页数|false|-
records|array|查询数据列表|false|-
└─cid|int64|班级id|false|-
└─cname|string|班级名称|false|-
└─uid|int64|班主任id|false|-
└─state|int32|班级状态|false|-
└─createTime|string|创建时间|false|-
└─updateTime|string|修改时间|false|-
└─yl1|string|预留|false|-
└─yl2|string|预留|false|-
└─yl3|string|预留|false|-
└─yl4|string|预留|false|-
total|int64|总数|false|-
size|int64|每页显示条数，默认 10|false|-
current|int64|当前页|false|-
orders|array|排序字段信息|false|-
└─column|string|需要进行排序的字段|false|-
└─asc|boolean|是否正序排列，默认 true|false|-
optimizeCountSql|boolean|自动优化 COUNT SQL|false|-
searchCount|boolean|是否进行 count 查询|false|-
hitCount|boolean|是否命中count缓存|false|-
countId|string|countId|false|-
maxLimit|int64|countId|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/class/list?pages=393&cid=275&cname=murray.larkin&uid=545&state=3&createTime=2023-02-05 21:13:37&updateTime=2023-02-05 21:13:37&yl1=4kvshz&yl2=0dpobv&yl3=hgxorw&yl4=dmx0bf&total=887&size=915&current=582&column=01xofm&asc=true&optimizeCountSql=true&searchCount=true&hitCount=true&countId=79&maxLimit=865&isSearchCount=true&records[0].state=3&records[0].yl3=azm1wz&records[0].cname=murray.larkin&records[0].uid=734&records[0].yl1=hv64c3&orders[0].asc=true&records[0].createTime=2023-02-05 21:13:37&records[0].yl4=ffhhzn&records[0].cid=458&records[0].updateTime=2023-02-05 21:13:37&records[0].yl2=776h0a&orders[0].column=3no45o
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 根据班级名称查询班级信息（模糊）
**URL:** `/admin/class/selectByKey`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 根据班级名称查询班级信息（模糊）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
selectKey|string|查询条件|false|-
page|int32|当前页|false|-
pageSize|int32|单页数据条数|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/class/selectByKey?selectKey=gbhhvh&page=1&pageSize=10
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 通过id查询班级
**URL:** `/admin/class/selectById`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过id查询班级

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
cid|int64|班级id|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/class/selectById?cid=287
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 新增班级
**URL:** `/admin/class/insert`

**Type:** `POST`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 新增班级

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
cid|int64|班级id|false|-
cname|string|班级名称|false|-
uid|int64|班主任id|false|-
state|int32|班级状态|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/class/insert --data '{
  "cid": 341,
  "cname": "murray.larkin",
  "uid": 541,
  "state": 3,
  "createTime": "2023-02-05 21:13:37",
  "updateTime": "2023-02-05 21:13:37",
  "yl1": "knhinq",
  "yl2": "7d8q77",
  "yl3": "iskswp",
  "yl4": "91y48r"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 通过id删除一条班级信息
**URL:** `/admin/class/del`

**Type:** `DELETE`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过id删除一条班级信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
cid|int64|cid|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/class/del?cid=363
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 批量删除班级
**URL:** `/admin/class/delAll`

**Type:** `DELETE`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 批量删除班级

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
cids|string|班级idList|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/class/delAll?cids=5
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

## 通过id修改班级信息
**URL:** `/admin/class/update`

**Type:** `PUT`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 通过id修改班级信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token令牌|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
cid|int64|班级id|false|-
cname|string|班级名称|false|-
uid|int64|班主任id|false|-
state|int32|班级状态|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X PUT -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/class/update --data '{
  "cid": 212,
  "cname": "murray.larkin",
  "uid": 323,
  "state": 3,
  "createTime": "2023-02-05 21:13:37",
  "updateTime": "2023-02-05 21:13:37",
  "yl1": "27os0m",
  "yl2": "n1afoc",
  "yl3": "4b4009",
  "yl4": "fpaw6r"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
mapKey|object|A map key.|-
└─any object|object|any object.|-

**Response-example:**
```
{
  "mapKey": {
    "waring": "You may use java.util.Object for Map value; smart-doc can't be handle."
  }
}
```

