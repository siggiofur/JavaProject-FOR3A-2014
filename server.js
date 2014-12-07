var app         = require('express')();
var express     = require('express');
var net         = require('net');
var http        = require('http').Server(app);
var io          = require('socket.io')(http);

// set the view engine to ejs
app.set('view engine', 'ejs');

app.set('port', process.env.PORT || 8080); // set our port

app.use(express.static('public'));

var router = express.Router(); // get an instance of the express Router

//routes
router.get('/', function(req, res) {
    console.log(req.ip + ' : req /');
    res.render('index');
});

// all of our routes will be prefixed with /api
app.use('/', router);

// comunitcate with client via socket
io.sockets.on('connection', function(socket) {
    console.log(socket.id + ' : connected');

    // connect to java chat server
    var javalink = net.createConnection('2222', 'localhost', function(argument) {
        console.log(socket.id + ' : javalink esablist');
    });

    javalink.on('data', function(data) {
        //console.log(data.toString());
        socket.emit('message', data.toString())
    });

    socket.on('message', function(msg) {
        //io.emit('message', msg);
        javalink.write(msg + '\r\n');
        console.log(socket.id + ' : says "' + msg + '"');
    });

    socket.on('disconnect', function(){
        console.log(socket.id + ' : disconnected');
        javalink.end();
    });
});

// Server start
http.listen(app.get('port'), function(){
    console.log('listening on *:' + app.get('port'));
});