window.onload = init;
var socket = new WebSocket("ws://192.168.0.45:8080/WebsocketHome/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var device = JSON.parse(event.data);
    if (device.action === "add") {
        printDeviceElement(device);
    }
    if (device.action === "remove") {
        document.getElementById(device.id).remove();
        //device.parentNode.removeChild(device);
    }
    if (device.action === "toggle") {
        var node = document.getElementById(device.id);
        var statusText = node.children[2];
        if (device.status === "On") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
        } else if (device.status === "Off") {
            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        }
    }
}

function addDevice(name, type, description) {
    var DeviceAction = {
        action: "add",
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(DeviceAction));
}

function removeDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
    var content = document.getElementById("content");
    
    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = device.name;
    deviceDiv.appendChild(deviceName);

    var deviceType = document.createElement("span");
    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

    var deviceStatus = document.createElement("span");
    if (device.status === "On") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
    } else if (device.status === "Off") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        //deviceDiv.setAttribute("class", "device off");
    }
    deviceDiv.appendChild(deviceStatus);

    var deviceDescription = document.createElement("span");
    deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
    deviceDiv.appendChild(deviceDescription);

    var removeDevice = document.createElement("span");
    removeDevice.setAttribute("class", "removeDevice");
    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
    deviceDiv.appendChild(removeDevice);
    
    var button = document.createElement("span");
    button.setAttribute("class","button");
    html = "<a href=\"#\" OnClick=sendConfirm("+device.id+",1)>Accept</a> <br/> <br/>"+
           "<a href=\"#\" OnClick=sendConfirm("+device.id+",2)>Reject</a>";
   
    button.innerHTML = html;
    deviceDiv.appendChild(button);
    prompt("Id: "+device.id);
    
}

function showForm() {
    document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
    var form = document.getElementById("loginForm");
    var name = form.elements["user_name"].value;
    
    //var name = form.elements["user_name"].value;
    alert("HOLAA "+name+" !");
    //document.getElementById("addDeviceForm").style.display = "none";
}

function formSubmit() {
    var form = document.getElementById("addDeviceForm");
    var name = form.elements["device_name"].value;
    var type = form.elements["device_type"].value;
    var description = form.elements["device_description"].value;
    hideForm();
    document.getElementById("addDeviceForm").reset();
    addDevice(name, type, description);
}

function init() {
    hideForm();
}

function logIn() {
    //socket = new WebSocket("ws://192.168.0.17:8080/WebsocketHome/actions");
    //socket.onmessage = onMessage;
    var form = document.getElementById("loginForm");
    
    var name = form.elements["user_name"].value;
    
    var User = {
        action: "login",
        user: name
    };
    socket.send(JSON.stringify(User));
    
}

function sendConfirm(mid,mvalue) {
    var form = document.getElementById("loginForm");
    var name = form.elements["user_name"].value;
    
    var Ans = {
        action: "answer",
        user: name,
        value: mvalue,
        id: mid
    }
    socket.send(JSON.stringify(Ans));
    prompt("implement it! id: "+id);
}
