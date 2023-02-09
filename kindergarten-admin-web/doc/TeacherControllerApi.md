
# 处理教师相关事务的Controller
2023/1/19
## 获取教师列表
**URL:** `/admin/teacher/list`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取教师列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-





**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/teacher/list
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

## 通过id修改教师信息
**URL:** `/admin/teacher/update`

**Type:** `PUT`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 通过id修改教师信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token令牌|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
uid|int64|用户id|false|-
username|string|用户账号|false|-
avatar|string|头像|false|-
mnemonicCode|string|用户助记码|false|-
password|string|密码|false|-
gander|string|性别|false|-
phone|string|手机号码|false|-
email|string|用户邮箱|false|-
roleid|int64|用户角色|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X PUT -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/teacher/update --data '{
  "uid": 156,
  "username": "murray.larkin",
  "avatar": "0raeeh",
  "mnemonicCode": "72637",
  "password": "v4zdgv",
  "gander": "2hlq9s",
  "phone": "1-216-682-1142",
  "email": "barton.bartoletti@hotmail.com",
  "roleid": 158,
  "createTime": "2023-02-05 21:13:38",
  "updateTime": "2023-02-05 21:13:38",
  "yl1": "s8myfr",
  "yl2": "ppjayf",
  "yl3": "v42djw",
  "yl4": "h55586"
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

## 批量删除
**URL:** `/admin/teacher/deleteAll`

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
uids|string|批量删除教师的id|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/teacher/deleteAll?uids=5
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

## 删除单条教师信息
**URL:** `/admin/teacher/delete`

**Type:** `DELETE`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 删除单条教师信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|验证令牌|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
uid|int64|教师id|false|-


**Request-example:**
```
curl -X DELETE -H 'X-Admin-Token' -i /admin/teacher/delete?uid=282
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

## 通过id查询教师
**URL:** `/admin/teacher/selectById`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过id查询教师

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
uid|int64|教师id|false|-


**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/teacher/selectById?uid=434
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

## 分页查询教师列表
**URL:** `/admin/teacher/teacherList`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 分页查询教师列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|token|true|-



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
pages|int64|当前分页总页数|false|-
records|array|查询数据列表|false|-
└─uid|int64|用户id|false|-
└─username|string|用户账号|false|-
└─avatar|string|头像|false|-
└─mnemonicCode|string|用户助记码|false|-
└─password|string|密码|false|-
└─gander|string|性别|false|-
└─phone|string|手机号码|false|-
└─email|string|用户邮箱|false|-
└─roleid|int64|用户角色|false|-
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
curl -X GET -H 'X-Admin-Token' -i /admin/teacher/teacherList?pages=56&uid=552&username=murray.larkin&avatar=putso7&mnemonicCode=72637&password=tldood&gander=9l8k56&phone=1-216-682-1142&email=barton.bartoletti@hotmail.com&roleid=324&createTime=2023-02-05 21:13:38&updateTime=2023-02-05 21:13:38&yl1=z8jepa&yl2=d18ikd&yl3=221e94&yl4=tuy3nv&total=680&size=140&current=648&column=gfjooy&asc=true&optimizeCountSql=true&searchCount=true&hitCount=true&countId=79&maxLimit=607&records[0].mnemonicCode=72637&records[0].yl2=gzbu9q&records[0].phone=1-216-682-1142&records[0].password=za0rtz&records[0].yl4=b0nvng&records[0].roleid=991&orders[0].asc=true&records[0].email=barton.bartoletti@hotmail.com&records[0].yl1=9u6a96&records[0].uid=686&records[0].createTime=2023-02-05 21:13:38&records[0].username=murray.larkin&records[0].yl3=8l42q2&records[0].updateTime=2023-02-05 21:13:38&records[0].gander=h3qef1&isSearchCount=true&records[0].avatar=5rutf5&orders[0].column=q41r96
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

## 通过搜索条件查询教师
**URL:** `/admin/teacher/selectTeacher`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 通过搜索条件查询教师

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
curl -X GET -H 'X-Admin-Token' -i /admin/teacher/selectTeacher?selectKey=jo2bo9&page=1&pageSize=10
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

## 新增教师
**URL:** `/admin/teacher/insert`

**Type:** `POST`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 新增教师

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|权限|true|-




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
uid|int64|用户id|false|-
username|string|用户账号|false|-
avatar|string|头像|false|-
mnemonicCode|string|用户助记码|false|-
password|string|密码|false|-
gander|string|性别|false|-
phone|string|手机号码|false|-
email|string|用户邮箱|false|-
roleid|int64|用户角色|false|-
createTime|string|创建时间|false|-
updateTime|string|修改时间|false|-
yl1|string|预留|false|-
yl2|string|预留|false|-
yl3|string|预留|false|-
yl4|string|预留|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -H 'X-Admin-Token' -i /admin/teacher/insert --data '{
  "uid": 901,
  "username": "murray.larkin",
  "avatar": "u5row7",
  "mnemonicCode": "72637",
  "password": "2u2r9j",
  "gander": "ckjy6d",
  "phone": "1-216-682-1142",
  "email": "barton.bartoletti@hotmail.com",
  "roleid": 707,
  "createTime": "2023-02-05 21:13:38",
  "updateTime": "2023-02-05 21:13:38",
  "yl1": "wiz3de",
  "yl2": "jg1kdn",
  "yl3": "bj1mpa",
  "yl4": "jujli6"
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

