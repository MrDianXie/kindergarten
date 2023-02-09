
# 登录验证Controller
## 
**URL:** `/admin/auth/verifyCode`

**Type:** `GET`

**Author:** XIE_HRZGZ

**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X GET -i /admin/auth/verifyCode
```

**Response-example:**
```
Return void.
```

## 验证登录
**URL:** `/admin/auth/login`

**Type:** `POST`

**Author:** XIE_HRZGZ

**Content-Type:** `application/json`

**Description:** 验证登录




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
username|string|No comments found.|false|-
password|string|No comments found.|false|-
autoLogin|boolean|No comments found.|false|-
code|string|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -i /admin/auth/login --data '{
  "username": "murray.larkin",
  "password": "29ux5x",
  "autoLogin": true,
  "code": "72637"
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

