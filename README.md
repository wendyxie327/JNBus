# JNBus


## 工作过程
获取最新版本号 —— 将版本号加入到头信息中 —— 获取服务IP地址：将IP地址和最新版本号保存在本地，当查询访问出现问题时，重复该过程

查询路线界面 —— 根据接口1，查询出符合规则的线路名称，标记起止地和路线id —— 用户选中路线名称，访问接口2和3，分别查询路线和当前车辆

## 接口地址
### 获取升级地址
http://jinan.iwaybook.com/download/update.json
返回数据
```
{
    "status": {
        "code": 0,
        "msg": ""
    },
    "result": {
        "versionCode": 2319,
        "versionName": "3.1.9",
        "descriptions": [
            "公交充值功能优化",
            "用户功能模块优化",
            "其他功能优化"
        ],
        "must": true,
        "url": "http://60.216.101.229/download/jinan.apk"
    }
}
```




**新版接口http请求中header中加入**

**key：version**

**value：android-insigma.waybook.jinan-231**


### 获取服务器IP地址
http://www.iwaybook.com/server-ue2/rest/servers-v2/370100
返回数据
```
{
    "status": {
        "code": 0
    },
    "result": {
        "id": 3,
        "name": "370100",
        "httpAddr": "60.216.101.229",
        "functions": "{\"bus\":{\"httpAddr\":\"60.216.101.229\",\"transfertype\":\"1\"}}",
        "state": 1,
        "useToken": false
    }
}
```



### 1. 查询接口
http://60.216.101.229/server-ue2/rest/buslines/simple/370100/1/0/20

|query|offset|len|
返回数据demo
```
{
    "status": {
        "code": 0,
        "msg": ""
    },
    "result": {
        "pageParam": {
            "offset": 0,
            "len": 20,
            "totalNum": 228
        },
        "result": [
            {
                "id": "577",
                "lineName": "1",
                "startStationName": "营市街经十路",
                "endStationName": "洪家楼",
                "updateTime": "Oct 22, 2016 09:12:39 PM"
            },
            {
                "id": "578",
                "lineName": "1",
                "startStationName": "洪家楼",
                "endStationName": "营市街经十路",
                "updateTime": "Oct 22, 2016 09:12:39 PM"
            },
            {
                "id": "556",
                "lineName": "10王辛",
                "startStationName": "洪家楼",
                "endStationName": "王辛",
                "updateTime": "Oct 22, 2016 09:12:38 PM"
            },...

        ]
    }
}
```

### 2. 线路接口
http://60.216.101.229/server-ue2/rest/buses/busline/370100/164
返回数据
```
{
    "status": {
        "code": 0,
        "msg": ""
    },
    "result": [
        {
            "busId": "k7273",
            "lng": 117.101201,
            "lat": 36.677868,
            "velocity": 19.676019841452977,
            "isArrvLft": "2", // 1没到，2到了
            "stationSeqNum": 13, // 站牌id，对应接口3中id字段
            "status": "1",
            "buslineId": "164",
            "actTime": "Nov 25, 2016 03:07:13 PM",
            "cardId": "k7273",
            "orgName": "",
            "averageVelocity": 15.483586342705129,
            "coordinate": 0
        },
        {
            "busId": "5765",
            "lng": 117.129258,
            "lat": 36.676758,
            "velocity": 13.867336626057812,
            "isArrvLft": "1",
            "stationSeqNum": 20,
            "status": "1",
            "buslineId": "164",
            "actTime": "Nov 25, 2016 03:07:56 PM",
            "cardId": "5765",
            "orgName": "",
            "averageVelocity": 15.589071640294328,
            "coordinate": 0
        },
        {
            "busId": "7543",
            "lng": 117.144258,
            "lat": 36.670646,
            "velocity": 33.701071845201724,
            "isArrvLft": "2",
            "stationSeqNum": 25,
            "status": "1",
            "buslineId": "164",
            "actTime": "Nov 25, 2016 03:08:03 PM",
            "cardId": "7543",
            "orgName": "",
            "averageVelocity": 14.875696305175177,
            "coordinate": 0
        },
        {
            "busId": "k7261",
            "lng": 117.040091,
            "lat": 36.665646,
            "velocity": 0,
            "isArrvLft": "2",
            "stationSeqNum": 2,
            "status": "1",
            "buslineId": "164",
            "actTime": "Nov 25, 2016 03:05:47 PM",
            "cardId": "k7261",
            "orgName": "",
            "averageVelocity": 15.847929480332265,
            "coordinate": 0
        },
        {
            "busId": "7560",
            "lng": 117.058701,
            "lat": 36.665646,
            "velocity": 19.14073608894609,
            "isArrvLft": "2",
            "stationSeqNum": 5,
            "status": "1",
            "buslineId": "164",
            "actTime": "Nov 25, 2016 03:07:14 PM",
            "cardId": "7560",
            "orgName": "",
            "averageVelocity": 14.50091218181641,
            "coordinate": 0
        }
    ]
}
```

### 3. 获取路线图
http://60.216.101.229/server-ue2/rest/buslines/370100/163
	获取内容与接口4相同

### 4. 返程接口
http://60.216.101.229/server-ue2/rest/buslines/theOtherDirection/370100/163
返回数据

```
{
    "status": {
        "code": 0,
        "msg": ""
    },
    "result": {
        "id": "164",
        "area": 370100,
        "lineName": "87",
        "startStationName": "解放桥东",
        "endStationName": "公交驾校",
        "stations": [
            {
                "id": "1",
                "area": 370100,
                "stationName": "解放桥东",
                "lng": 117.0509284605,
                "lat": 36.672373539354,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "2",
                "area": 370100,
                "stationName": "中心医院",
                "lng": 117.0537313234,
                "lat": 36.671967209268,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },....
        ],
        "ticketPrice": "87:票价一元  K87:票价两元",
        "operationTime": "公交驾校  : 6:00－20:30 解放桥东 : 6:00－21:05",
        "owner": "",
        "linePoints": "117.0509284605_36.672373539354,117.0537313234_36.671967209268,117.06368680755_36.671819906915,117.06794374589_36.6717998599,117.07227812572_36.671798665915,117.07675074293_36.671797624335,117.08263804947_36.675358927633,117.08875666528_36.678626857988,117.09348563332_36.680280368361,117.0977335111_36.68117883938,117.1013201248_36.681947765058,117.10941803464_36.683641521915,117.11535228146_36.684900142168,117.1193322849_36.685690822768,117.122214434_36.684974696277,117.12569788496_36.682870024501,117.1313921296_36.683880973574,117.13373623315_36.685027830243,117.13629182577_36.683483957146,117.14411384776_36.684563365541,117.14941843781_36.687066148683,117.15341459082_36.688939047543,117.15550141117_36.685761362256,117.15628724344_36.680748557474,117.15684010964_36.677748209331,117.15739464336_36.674708060659",
        "description": "",
        "updateOwner": "sys-upgrade",
        "updateTime": "Oct 22, 2016 09:12:28 PM",
        "state": "0"
    }
}
```
