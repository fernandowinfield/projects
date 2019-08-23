/** CLIENT CONFIGURATION - connect to the server */
const socketIOClient = require("socket.io-client");

// When deployed, connect to the hosted server, otherwise connect to local server
// Localhost port must match server
let host = process.env.NODE_ENV === 'production' ?
    "https://court-finder-app.herokuapp.com/" : "localhost:4002"
let socket = socketIOClient.connect(host, {secure: true});
// Checks which host we're connected to (for troubleshooting);
console.log("Connected to " + host);




socket.on("welcome", msg => {
  console.log(msg);
});


export const checkInOut = (courtId, userId, callback) => {
  socket.emit("check in out", {courtId: courtId, userId: userId});

  socket.on("update courts map", courtsMap => {
    callback(courtsMap);
  });
}

export const logMessage = msg => {
  console.log(msg);
}
