
# 
## 分页查询公告列表
**URL:** `/admin/affiche/list`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 分页查询公告列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
pages|int64|当前分页总页数|false|-
records|array|查询数据列表|false|-
└─aid|int64|公告id|false|-
└─uid|int64|公告发布人id|false|-
└─title|string|公告标题|false|-
└─body|string|公告内容|false|-
└─state|int32|公告状态|false|-
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
curl -X GET -H 'X-Admin-Token' -i /admin/affiche/list?pages=144&aid=467&uid=770&title=zcdovb&body=xuwy1i&state=3&createTime=2023-02-05 21:13:37&updateTime=2023-02-05 21:13:37&yl1=vs3s8q&yl2=fdk45f&yl3=3tnek9&yl4=8o6omj&total=715&size=366&current=703&column=etlew3&asc=true&optimizeCountSql=true&searchCount=true&hitCount=true&countId=79&maxLimit=476&records[0].updateTime=2023-02-05 21:13:37&records[0].yl1=90zbbf&records[0].title=1qjt07&records[0].aid=351&records[0].uid=685&records[0].state=3&records[0].body=z6bl8o&records[0].yl2=znvqkg&orders[0].asc=true&records[0].createTime=2023-02-05 21:13:37&records[0].yl4=qny46v&records[0].yl3=zzd3jt&isSearchCount=true&orders[0].column=2yseds
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

## 查询公告（模糊）
**URL:** `/admin/affiche/selectByKey`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询公告（模糊）

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
curl -X GET -H 'X-Admin-Token' -i /admin/affiche/selectByKey?selectKey=jywkze&page=1&pageSize=10
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

## 通过id查询
**URL:** `/admin/affiche/selectById`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过id查询

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
aid|int64|公告id|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/affiche/selectById?aid=573
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

## 新增公告
**URL:** `/admin/affiche/insert`

**Type:** `POST`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 新增公告

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
aid|int64|公告id|false|-
uid|int64|公告发布人id|false|-
title|string|公告标题|false|-
body|string|公告内容|false|-
state|int32|公告状态|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/affiche/insert --data '{
  "aid": 319,
  "uid": 57,
  "title": "s7aw6l",
  "body": "6b943v",
  "state": 3,
  "createTime": "2023-02-05 21:13:37",
  "updateTime": "2023-02-05 21:13:37",
  "yl1": "3rpwbt",
  "yl2": "jz58pd",
  "yl3": "tdp3z9",
  "yl4": "pnazjm"
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
**URL:** `/admin/affiche/del`

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
aid|int64|公告id|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/affiche/del?aid=366
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
**URL:** `/admin/affiche/delAll`

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
aids|string|公告ids|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/affiche/delAll?aids=5
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
**URL:** `/admin/affiche/update`

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
aid|int64|公告id|false|-
uid|int64|公告发布人id|false|-
title|string|公告标题|false|-
body|string|公告内容|false|-
state|int32|公告状态|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X PUT -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/affiche/update --data '{
  "aid": 34,
  "uid": 977,
  "title": "e9e5do",
  "body": "40w7t2",
  "state": 3,
  "createTime": "2023-02-05 21:13:37",
  "updateTime": "2023-02-05 21:13:37",
  "yl1": "8mc8m1",
  "yl2": "ex8n8b",
  "yl3": "w212mh",
  "yl4": "a0hw7e"
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

