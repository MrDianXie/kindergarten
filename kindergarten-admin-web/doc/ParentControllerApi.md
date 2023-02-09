
# 处理家长相关事务
## 
**URL:** `/admin/parent/list`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
X-Admin-Token|string|null|true|-





**Request-example:**
```
curl -X GET -H 'X-Admin-Token' -i /admin/parent/list
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

