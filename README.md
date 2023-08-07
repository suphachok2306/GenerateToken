
# Generate Token

### ขั้นตอนการรันทดสอบ

 - **Connect to database**

**1** **.** ทำการดาวน์โหลดและติดตั้ง PostgreSQl 13.11 

    https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

**2** **.** ทำการดาวน์โหลดและติดตั้ง Debeaver 

    https://dbeaver.io/download/

**3** **.** เข้าโฟล์เดอร์ที่ติดตั้ง PostgreSQl เปิดใช้งาน pgAdmin4 ทำการสร้าง user โดยกำหนดให้ 

      username: ssodev
      password: sso2022ok

**4** **.** สร้าง 

    database: ssotest

**5** **.** เปิด Debeaver เชื่อม database โดยกำหนดให้

      host: localhost
      port: 5432
      database: ssotest
      username: ssodev
      password: sso2022ok

**6** **.** สร้าง Table: sso_user_test ด้วยคำสั่ง SQL นี้

    create table sso_user_test
    (
        request_date          timestamp not null
            constraint pk_sso_user_test
                primary key,
        ssotype               varchar(50),
        systemid              varchar(50),
        systemname            varchar(250),
        systemtransactions    varchar(250),
        systemprivileges      varchar(250),
        systemusergroup       varchar(50),
        systemlocationgroup   varchar(50),
        userid                varchar(200),
        userfullname          varchar(500),
        userrdofficecode      varchar(250),
        userofficecode        varchar(250),
        clientlocation        varchar(250),
        locationmachinenumber varchar(500),
        tokenid               varchar(1000)
    );

--------------------------------------------------------------------------------------------------------

 - **Run project**

**1.**  กด Run ที่ class TokenApplication เพื่อใช้งาน Spring Boot และเปิดเซิร์ฟเวอร์

**2.** ใช้งาน swagger โดยเข้าลิ้งนี้ `http://localhost:8080/apitest/swagger-ui.html`

**3.** เลือกใช้งาน `POST api/generateToken`

**4.** ทำการใส่ข้อมูลใน Request body
**4.1** ถ้าใส่ข้อมูลครบ จะทำการเพิ่มข้อมูลได้ และบันทึกลง database โดยใน swagger จะขึ้นแบบนี้
  ```
   Ex.เพิ่มข้อมูลสำเร็จ
{ "responseCode": "I07000", 
"responseMessage": "ทำรายการเรียบร้อย", 
"responseData": {
	"userId": "p123", 
	"tokenId": "2306pppp222" }
 }
```

**4.2** ถ้าใส่ข้อมูลไม่ครบ จะเพิ่มข้อมูลไม่ได้ และไม่ได้บันทึกลง database โดยใน swagger จะขึ้นแบบนี้
  ```
   Ex.เพิ่มข้อมูลไม่สำเร็จ HTTP 400
{
  "responseCode": "E00001",
  "responseMessage": "ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ กรอกข้อมูลไม่ครบ",
  "responseData": {
    "userId": null,
    "tokenId": null }
}
```

**4.3** เมื่อกดเพิ่มข้อมูล แล้วมีข้อผิดพลาดภายในเซิร์ฟเวอร์  โดยใน swagger จะขึ้นแบบนี้
  ```
   Ex.เพิ่มข้อมูลไม่สำเร็จ HTTP 500
{
  "responseCode": "E00001",
  "responseMessage": "ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ มีข้อผิดพลาดภายในเซิร์ฟเวอร์",
  "responseData": {
    "userId": uuu2,
    "tokenId": s1a222 }
}
```

----------------------


