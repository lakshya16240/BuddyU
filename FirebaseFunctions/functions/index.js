let functions = require('firebase-functions');
var admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase)

exports.newMessage = functions.database.ref('Notification')
	.onUpdate(event => {
        console.log(event.after._data)
        sendNotification(event.after._data)
        return true;
	})


	function sendNotification(msg) {

		let message = {
			data: {
				title: msg["topic"],
                body: msg["msg"],
                sender: msg["name"]
			},
			topic : "android" 
        }
        
        return admin.messaging().send(message)
        .then((response) => {
            // Response is a message ID string.
            console.log('Successfully sent message:', response);
        })
        .catch((error) => {
            console.log('Error sending message:', error);
        });
        
    }
    
    // https://firebase.google.com/docs/cloud-messaging/admin/send-messages
    // for future references