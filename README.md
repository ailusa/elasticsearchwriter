# elasticsearchwriter
定制化datax 向elasticsearch  写入数据


官网git上只在5.x上进行了测试，因为业务需要，这个工程做了一些改动. 
实际使用es版本为 6.4.3

1：meter_user 表导入时，会计算坐标
2：创建index的mapping 时，会增加时间format
3：导入数据时，会根据时间的 format 进行格式化
4：使用非默认创建index规则时，id使用数据库表id
