function scanDevices() {
  navigator.bluetooth
    .requestDevice({
      filters: [
        { namePrefix: ["abeacon"], optionalServices: ["battery_service"] }
      ]
    })
    .then(device => {
      console.log(device);
      $(".device-name").val(device.name);
      return device.gatt.connect()
    }).then(server =>{
console.log(server)
    })
    .catch(error => {
      console.log(error);
    });
}
