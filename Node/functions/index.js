const functions = require('firebase-functions');
const crednetials = require('./credentials.json')
var admin = require("firebase-admin");

admin.initializeApp({
    credential: admin.credential.cert(crednetials),
    databaseURL: "https://arqui2umg.firebaseio.com"
});

exports.sendFancyMessage = functions.https.onRequest((req, res) => {

        var date = new Date();
        var monthNames = [
            "Enero", "Febrero", "Marzo",
            "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre",
            "Noviembre", "Diciembre"
        ];

        var day = date.getDate();
        var monthIndex = date.getMonth();
        var year = date.getFullYear();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();

        var myDate  = day + ' ' + monthNames[monthIndex] + ' ' + year + ' ' + hour + ':' + minute + ':'+second;
            
  
        var message = {
            notification:{
                    title: '¡¡¡ Alerta !!!',
                    body:'Se ha detectado un intruso!'
                },
            data:{
                    texto:'Se ha detectado un intruso el ' + myDate
                },
            topic: 'news'
        }


        return  admin.messaging().send(message)
                .then((response) => {
                    console.log('Successfully sent message:', response);
                    return res.json(202,{ message:"Successfully sent message",response })
                })
                .catch((error) => {
                    console.log('Error sending message:', error);
                    return res.json(500,{ message:"Error excecutiong fcm. please see the logs." })
                });
});