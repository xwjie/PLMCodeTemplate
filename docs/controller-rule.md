# Controller规范

## 1. **所有函数返回统一的ResultBean**



## **2. ResultBean是controller专用的，不允许往后传**



## **3. Controller只做参数格式的转换**

**不允许把json，map这类对象传到services去，也不允许services返回json、map。**

## 4. 可以不**打印日志**



