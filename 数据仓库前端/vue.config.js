module.exports = {

    // 配置跨域请求
    devServer: {
        port: 8090,
        host: 'localhost',
        open: true,
        https: false,
        proxy:{
            '/mysql': {
                target: 'http://localhost:8085/',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/mysql': ''
                }
            },
            '/neo4j': {
                target: 'http://localhost:8086/',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/neo4j': ''
                }
            },
            '/hive': {
                target: 'http://localhost:8087/',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/hive': ''
                }
            }
        },
    }
}
