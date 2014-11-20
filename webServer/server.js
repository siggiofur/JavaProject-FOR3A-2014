var app 		= require('express')();
var express		= require('express');
var http 		= require('http').Server(app);
var io 			= require('socket.io')(http);
var bodyParser  = require('body-parser');    // importing body-parser

// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// set the view engine to ejs
app.set('view engine', 'ejs');

app.set('port', process.env.PORT || 80); // set our port

app.use(express.static('public'));

var router = express.Router(); // get an instance of the express Router

//routes
router.get('/', function(req, res) {
	res.render('index'); 
});

// all of our routes will be prefixed with /api
app.use('/', router);

io.on('connection', function(socket){
	console.log('user connected');

	socket.on('chat message', function(msg){
		io.emit('chat message', msg);
		console.log('message: ' + msg);
	});

	socket.on('disconnect', function(){
		console.log('user disconnected');
	});
});

http.listen(app.get('port'), function(){
	console.log('listening on *:' + app.get('port'));
});

// Server start
//app.listen(app.get('port'), '0.0.0.0');
//console.log('Magic happens on port ' + app.get('port'));