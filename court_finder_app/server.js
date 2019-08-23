/** SERVER CONFIGURATION */
const express = require("express");
const app = express();
const server = require('http').Server(app);
const io = require('socket.io')(server);
const path = require("path");

// Choose a port, default is 4002 (could be almost anything)
const PORT = process.env.PORT || 4002;

app.use(express.static(path.join(__dirname, 'build')));

// When on Heroku, serve the UI from the build folder
if (process.env.NODE_ENV === 'production') {  
    app.use(express.static(path.join(__dirname, 'build')));  
    app.get('*', (req, res) => {    
        res.sendfile(path.join(__dirname = 'build/index.html'));  
    })
}

// When on local host, server from the public folder. 
// Rule will not be written if production conditional has executed
app.get('*', (req, res) => {  
    app.sendFile(path.join(__dirname+'public/index.html'));
})


// Listen for client connections
server.listen(PORT, () => console.log(`Listening on ${ PORT }`));

let courtsAttendance = {};


io.on("connection", client => {
    // Send messages to and receive messages from the client in here
    client.emit("welcome", "Welcome. You are connected to the server");

    client.on("check in out", (ids) => {
        // update newly connected client
        if (ids.userId === "newClient") {
            io.sockets.emit("update courts map", courtsAttendance);
        }
        else {
            // check in/out existing client
            if (courtsAttendance[ids.courtId] === undefined) {
                courtsAttendance[ids.courtId] = [ids.userId];
            }
            else {
                if (courtsAttendance[ids.courtId].includes(ids.userId)) {
                    // user is leaving court
                    const index = courtsAttendance[ids.courtId].indexOf(ids.userId);
                    courtsAttendance[ids.courtId].splice(index, 1);
                }
                else {
                    // user arrived to the court
                    courtsAttendance[ids.courtId].push(ids.userId);
                }
            }
            io.sockets.emit("update courts map", courtsAttendance);
        }
    });
})