<%--
  Created by IntelliJ IDEA.
  User: marija
  Date: 7/4/2023
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
  <!--Stylesheet-->
  <style media="screen">
    *,
    *:before,
    *:after{
      padding: 0;
      margin: 0;
      box-sizing: border-box;
    }
    body{
      background-color: #FAF9F6;
    }
    .background{
      width: 430px;
      height: 520px;
      position: absolute;
      transform: translate(-50%,-50%);
      left: 50%;
      top: 50%;
    }
    .background .shape{
      height: 200px;
      width: 200px;
      position: absolute;
      border-radius: 50%;
    }
    .shape:first-child{
      background: linear-gradient(
              #EE4B2B,
              #D22B2B
      );
      left: -80px;
      top: -80px;
    }
    .shape:last-child{
      background: linear-gradient(
              to right,
              #ffff00,
              #e5e500
      );
      right: -30px;
      bottom: -80px;
    }
    form{
      height: 520px;
      width: 400px;
      background-color: rgba(255,255,255,0.13);
      position: absolute;
      transform: translate(-50%,-50%);
      top: 50%;
      left: 50%;
      border-radius: 10px;
      backdrop-filter: blur(10px);
      border: 2px solid rgb(233, 220, 201);
      box-shadow: 0 0 40px #191919;
      padding: 50px 35px;
    }
    form *{
      font-family: 'Poppins',sans-serif;
      color: #191919;
      letter-spacing: 0.5px;
      outline: none;
      border: none;
    }
    form h3{
      font-size: 32px;
      font-weight: 500;
      line-height: 42px;
      text-align: center;
    }

    label{
      display: block;
      margin-top: 30px;
      font-size: 16px;
      font-weight: 500;
    }
    input{
      display: block;
      height: 50px;
      width: 100%;
      background-color:rgb(226, 223, 210);
      border-radius: 3px;
      padding: 0 10px;
      margin-top: 8px;
      font-size: 14px;
      font-weight: 300;
    }
    ::placeholder{
      color: #323232;
    }
    button{
      margin-top: 50px;
      width: 100%;
      background-color: #323232;
      color: #ffffff;
      padding: 15px 0;
      font-size: 18px;
      font-weight: 600;
      border-radius: 5px;
      cursor: pointer;
    }
    .social{
      margin-top: 30px;
      display: flex;
    }
    .social div{
      background: red;
      width: 150px;
      border-radius: 3px;
      padding: 5px 10px 10px 5px;
      background-color: rgba(255,255,255,0.27);
      color: #eaf0fb;
      text-align: center;
    }
    .social div:hover{
      background-color: rgba(255,255,255,0.47);
    }
    .social .fb{
      margin-left: 25px;
    }
    .social i{
      margin-right: 4px;
    }

  </style>
</head>
<body>
<div class="background">
  <div class="shape"></div>
  <div class="shape"></div>
</div>
<form action="login" method="post">
  <h2>Greska!</h2>
  <h5>Moguće je da ste uneli username koji već postoji! Proverite da li ste popunili sva polja!</h5>

</form>
</body>
</html>
