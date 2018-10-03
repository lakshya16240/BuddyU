let functions = require('firebase-functions');
var admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase)

exports.newMessage = functions.database.ref('users/{userID}/Notification')
	.onUpdate(event => {
        sendNotification(event.after._data)
        return true;
	})


	function sendNotification(msg) {
		let subtitle = msg

		let message = {
			data: {
				title: "BuddyU",
				body: subtitle
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