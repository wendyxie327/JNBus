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



### 1. 查询接口
http://60.216.101.229/server-ue2/rest/buslines/simple/370100/1/0/20

|query|offset|len|



### 3. 获取路线图
http://60.216.101.229/server-ue2/rest/buslines/370100/163
	获取内容与接口4相同

### 4. 返程接口
http://60.216.101.229/server-ue2/rest/buslines/theOtherDirection/370100/163



## 新版本

### 济莱城际快车
http://58.56.100.251:9997/mobile/map/findcarLoc.do?drivewayId=402881ed573abdee01573b3c0e9d0004

http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402881ed573abdee01573b3c0e9d0004

/mobile/point/getPointByDrivewayId.do

      <td bgcolor="#FBF7F7"><h2><font size="7">长清公交</font></h2></td>
    </tr>
    
    <tr>
      <td><h2><font size="6">&nbsp; &nbsp; &nbsp; &nbsp; <a href="http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402816e65af36899015af4ad5cac01dd">1.K104路</a></font>（黄河浮桥——园博园北门）</h2></td>
    </tr>
<tr>
      <td><h2><font size="6">&nbsp; &nbsp; &nbsp; &nbsp; <a href="http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402816e65af36899015af46c3b5a00c9">2.102路</a></font>（长清汽车站——长清汽车站 ）</h2></td>
    </tr>
<tr>
      <td><h2><font size="6">&nbsp; &nbsp; &nbsp; &nbsp; <a href="http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402881f9591a3d7601591a8763900005">3.105路</a></font>（长清汽车站——娘娘店）</h2></td>
    </tr>
 <tr>
      <td><h2><font size="6">&nbsp; &nbsp; &nbsp; &nbsp; <a href="http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402881f9591a3d7601591ac2492a036f">4.1路</a></font>（济南长途总站——长清汽车站）</h2></td>
    </tr>
   <tr>
      <td><h2><font size="6">&nbsp; &nbsp; &nbsp; &nbsp; <a href="http://58.56.100.251:9997/mobile/point/getPoints.do?drivewayId=402881f9591aca4b01591ae50f490026">5.2路</a></font>（长途汽车总站——长清汽车站）</h2></td>