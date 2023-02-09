
# 处理学生相关事务的Controller
## 获取学生列表
**URL:** `/admin/student/studentList`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取学生列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token令牌|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
pages|int64|当前分页总页数|false|-
records|array|查询数据列表|false|-
└─sid|int64|学生id|false|-
└─sname|string|学生名字|false|-
└─gander|string|学生性别|false|-
└─age|int32|学生年龄|false|-
└─address|string|学生家庭住址|false|-
└─cid|int64|班级id|false|-
└─uid|int64|学生家长|false|-
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
curl -X GET -H 'X-Admin-Token' -i /admin/student/studentList?pages=478&sid=494&sname=murray.larkin&gander=fu039c&age=60&address=Apt. 623 812 Kulas Well， New Elliotmouth， NH 30770&cid=871&uid=305&createTime=2023-02-05 21:13:37&updateTime=2023-02-05 21:13:37&yl1=hkpviq&yl2=3292rf&yl3=8m5fec&yl4=b1mpam&total=237&size=264&current=877&column=2beqs3&asc=true&optimizeCountSql=true&searchCount=true&hitCount=true&countId=79&maxLimit=2&records[0].gander=jfnjeu&orders[0].asc=true&records[0].yl1=gbkgq5&records[0].yl3=xinwhg&records[0].yl4=thpm31&records[0].sid=624&records[0].sname=murray.larkin&records[0].cid=674&records[0].address=Apt. 623 812 Kulas Well， New Elliotmouth， NH 30770&records[0].createTime=2023-02-05 21:13:37&isSearchCount=true&records[0].age=60&orders[0].column=acqkoq&records[0].uid=638&records[0].yl2=uihx4k&records[0].updateTime=2023-02-05 21:13:37
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

## 
**URL:** `/admin/student/selectByKey`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token令牌|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
selectKey|string|查询条件|false|-
page|int32|当前页|false|-
pageSize|int32|每页信息条数|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/student/selectByKey?selectKey=p8b2u3&page=1&pageSize=10
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

## 通过id查询学生
**URL:** `/admin/student/selectById`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过id查询学生

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sid|int64|学生id|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/student/selectById?sid=246
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

## 新增一条学生数据
**URL:** `/admin/student/insert`

**Type:** `POST`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 新增一条学生数据

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sid|int64|学生id|false|-
sname|string|学生名字|false|-
gander|string|学生性别|false|-
age|int32|学生年龄|false|-
address|string|学生家庭住址|false|-
cid|int64|班级id|false|-
uid|int64|学生家长|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/student/insert --data '{
  "sid": 982,
  "sname": "murray.larkin",
  "gander": "7k0mrc",
  "age": 60,
  "address": "Apt. 623 812 Kulas Well， New Elliotmouth， NH 30770",
  "cid": 290,
  "uid": 295,
  "createTime": "2023-02-05 21:13:37",
  "updateTime": "2023-02-05 21:13:37",
  "yl1": "kc6lrv",
  "yl2": "b7osfq",
  "yl3": "k0kclc",
  "yl4": "ynp54r"
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

## 删除一条学生数据
**URL:** `/admin/student/del`

**Type:** `DELETE`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 删除一条学生数据

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sid|int64|学生id|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/student/del?sid=54
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

## 批量删除
**URL:** `/admin/student/delAll`

**Type:** `DELETE`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 批量删除

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|验证令牌|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sids|string|批量删除学生的id|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/student/delAll?sids=5
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

## 通过id修改学生信息
**URL:** `/admin/student/update`

**Type:** `PUT`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 通过id修改学生信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token令牌|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sid|int64|学生id|false|-
sname|string|学生名字|false|-
gander|string|学生性别|false|-
age|int32|学生年龄|false|-
address|string|学生家庭住址|false|-
cid|int64|班级id|false|-
uid|int64|学生家长|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X PUT -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/student/update --data '{
  "sid": 477,
  "sname": "murray.larkin",
  "gander": "qvy2ge",
  "age": 60,
  "address": "Apt. 623 812 Kulas Well， New Elliotmouth， NH 30770",
  "cid": 348,
  "uid": 695,
  "createTime": "2023-02-05 21:13:38",
  "updateTime": "2023-02-05 21:13:38",
  "yl1": "jh99dr",
  "yl2": "tmt6lw",
  "yl3": "xsgq4h",
  "yl4": "mbfib2"
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

