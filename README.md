# JNBus



## 获取升级地址
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


## 获取服务器IP地址
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



## 1. 查询接口
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
            },
            {
                "id": "557",
                "lineName": "10王辛",
                "startStationName": "王辛",
                "endStationName": "洪家楼",
                "updateTime": "Oct 22, 2016 09:12:38 PM"
            },
            {
                "id": "785",
                "lineName": "10支",
                "startStationName": "柿子园村",
                "endStationName": "洪家楼",
                "updateTime": "Oct 22, 2016 09:12:45 PM"
            },
            {
                "id": "786",
                "lineName": "10支",
                "startStationName": "洪家楼",
                "endStationName": "柿子园村",
                "updateTime": "Oct 22, 2016 09:12:45 PM"
            },
            {
                "id": "530",
                "lineName": "11",
                "startStationName": "十里河",
                "endStationName": "火车站",
                "updateTime": "Oct 22, 2016 09:12:37 PM"
            },
            {
                "id": "531",
                "lineName": "11",
                "startStationName": "火车站",
                "endStationName": "十里河",
                "updateTime": "Oct 22, 2016 09:12:37 PM"
            },
            {
                "id": "662",
                "lineName": "12",
                "startStationName": "东宇花园",
                "endStationName": "五柳闸",
                "updateTime": "Oct 22, 2016 09:12:42 PM"
            },
            {
                "id": "663",
                "lineName": "12",
                "startStationName": "五柳闸",
                "endStationName": "东宇花园",
                "updateTime": "Oct 22, 2016 09:12:42 PM"
            },
            {
                "id": "23",
                "lineName": "13",
                "startStationName": "世茂原山首府",
                "endStationName": "青年桥",
                "updateTime": "Oct 22, 2016 09:12:21 PM"
            },
            {
                "id": "24",
                "lineName": "13",
                "startStationName": "青年桥",
                "endStationName": "世茂原山首府",
                "updateTime": "Oct 22, 2016 09:12:22 PM"
            },
            {
                "id": "518",
                "lineName": "14",
                "startStationName": "青年桥",
                "endStationName": "水屯北路",
                "updateTime": "Oct 22, 2016 09:12:37 PM"
            },
            {
                "id": "519",
                "lineName": "14",
                "startStationName": "水屯北路",
                "endStationName": "青年桥",
                "updateTime": "Oct 22, 2016 09:12:37 PM"
            },
            {
                "id": "27",
                "lineName": "15",
                "startStationName": "动物园",
                "endStationName": "省体育中心",
                "updateTime": "Oct 22, 2016 09:12:22 PM"
            },
            {
                "id": "28",
                "lineName": "15",
                "startStationName": "省体育中心",
                "endStationName": "动物园",
                "updateTime": "Oct 22, 2016 09:12:22 PM"
            },
            {
                "id": "579",
                "lineName": "16",
                "startStationName": "小清河盐仓码头",
                "endStationName": "青年桥",
                "updateTime": "Oct 22, 2016 09:12:39 PM"
            },
            {
                "id": "580",
                "lineName": "16",
                "startStationName": "青年桥",
                "endStationName": "小清河盐仓码头",
                "updateTime": "Oct 22, 2016 09:12:39 PM"
            },
            {
                "id": "31",
                "lineName": "17",
                "startStationName": "重汽技术中心",
                "endStationName": "大涧沟东小学",
                "updateTime": "Oct 28, 2016 01:11:31 PM"
            },
            {
                "id": "32",
                "lineName": "17",
                "startStationName": "大涧沟东小学",
                "endStationName": "重汽技术中心",
                "updateTime": "Oct 28, 2016 01:11:35 PM"
            }
        ]
    }
}
```

## 2. 线路接口
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
            "isArrvLft": "2",
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

## 3. 返程接口
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
            },
            {
                "id": "3",
                "area": 370100,
                "stationName": "解放路山大路",
                "lng": 117.06368680755,
                "lat": 36.671819906915,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "4",
                "area": 370100,
                "stationName": "东郊饭店",
                "lng": 117.06794374589,
                "lat": 36.6717998599,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "5",
                "area": 370100,
                "stationName": "公交总公司",
                "lng": 117.07227812572,
                "lat": 36.671798665915,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "6",
                "area": 370100,
                "stationName": "甸柳庄",
                "lng": 117.07675074293,
                "lat": 36.671797624335,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "7",
                "area": 370100,
                "stationName": "工业南路西口",
                "lng": 117.08263804947,
                "lat": 36.675358927633,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "8",
                "area": 370100,
                "stationName": "七里河",
                "lng": 117.08875666528,
                "lat": 36.678626857988,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "9",
                "area": 370100,
                "stationName": "工业南路华信路",
                "lng": 117.09348563332,
                "lat": 36.680280368361,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "10",
                "area": 370100,
                "stationName": "济钢二分厂",
                "lng": 117.0977335111,
                "lat": 36.68117883938,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "11",
                "area": 370100,
                "stationName": "工业南路化纤厂路",
                "lng": 117.1013201248,
                "lat": 36.681947765058,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "12",
                "area": 370100,
                "stationName": "丁家庄西",
                "lng": 117.10941803464,
                "lat": 36.683641521915,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "13",
                "area": 370100,
                "stationName": "丁家庄东",
                "lng": 117.11535228146,
                "lat": 36.684900142168,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "14",
                "area": 370100,
                "stationName": "工业南路正丰路",
                "lng": 117.1193322849,
                "lat": 36.685690822768,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "15",
                "area": 370100,
                "stationName": "电子机械工程学校",
                "lng": 117.122214434,
                "lat": 36.684974696277,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "16",
                "area": 370100,
                "stationName": "天辰路西口",
                "lng": 117.12569788496,
                "lat": 36.682870024501,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "17",
                "area": 370100,
                "stationName": "天辰路崇华路",
                "lng": 117.1313921296,
                "lat": 36.683880973574,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "18",
                "area": 370100,
                "stationName": "天辰路舜华路",
                "lng": 117.13373623315,
                "lat": 36.685027830243,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "19",
                "area": 370100,
                "stationName": "齐鲁软件园北",
                "lng": 117.13629182577,
                "lat": 36.683483957146,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "20",
                "area": 370100,
                "stationName": "新泺大街颖秀路",
                "lng": 117.14411384776,
                "lat": 36.684563365541,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "21",
                "area": 370100,
                "stationName": "新泺大街开拓路",
                "lng": 117.14941843781,
                "lat": 36.687066148683,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "22",
                "area": 370100,
                "stationName": "新泺大街东口",
                "lng": 117.15341459082,
                "lat": 36.688939047543,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "23",
                "area": 370100,
                "stationName": "刘智远村北",
                "lng": 117.15550141117,
                "lat": 36.685761362256,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "24",
                "area": 370100,
                "stationName": "凤凰路舜风路",
                "lng": 117.15628724344,
                "lat": 36.680748557474,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "25",
                "area": 370100,
                "stationName": "刘智远村",
                "lng": 117.15684010964,
                "lat": 36.677748209331,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            },
            {
                "id": "26",
                "area": 370100,
                "stationName": "公交驾校",
                "lng": 117.15739464336,
                "lat": 36.674708060659,
                "buslines": "",
                "state": "0",
                "updateTime": "Oct 22, 2016 09:12:28 PM",
                "distance": 0,
                "busLineList": []
            }
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
