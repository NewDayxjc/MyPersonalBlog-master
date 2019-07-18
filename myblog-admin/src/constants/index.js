
let ONLINEURL = "http://39.96.94.165/"       //硬件地址
 

// 开发环境的常量地址
if (process.env.NODE_ENV == "development") {
    ONLINEURL = "http://localhost:8081"
 
}

export default {
    ONLINEURL,
}