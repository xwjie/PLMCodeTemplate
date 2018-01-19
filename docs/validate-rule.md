# 参数校验规范



# 参数校验不要放在Controller

统一放在Service上。因为Service会重用，而Controller不会重用。Controller只做参数转换工作。

# 使用自己的校验函数

不要直接使用第三方的校验工具库。









