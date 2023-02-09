
# CommonController
用于处理通用事务
## 获取头像
**URL:** `/admin/home/getAvatar`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取头像



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
username|string|用户名|false|-


**Request-example:**
```
curl -X GET -i /admin/home/getAvatar?username=murray.larkin
```

**Response-example:**
```
Return void.
```

## 验证用户信息
**URL:** `/admin/home/whoAmI`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 验证用户信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|验证令牌|true|-





**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/home/whoAmI
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

