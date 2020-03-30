function scanDevices() {
  navigator.bluetooth
    .requestDevice({
      filters: [{ namePrefix: ["abeacon"] }]
    })
    .then(device => {
      console.log(device);
      $(".device-name").val(device.name);
      return device.gatt.connect();
    })
    .then(server => {
      // Getting device information
      console.log(server)
      return server.getPrimaryService("device_information");
    })
    .then(service => {
      console.log(service)
      // Getting serialNumber
      return service.getCharacteristic("serial_number_string");
    })
    .then(characteristic => {
      console.log(characteristic)
      // Reading serialNumber
      return characteristic.readValue();
    })
    .then(value => {
      console.log("Serial Number is " + value.getUint8(0));
    })
    .catch(error => {
      console.log(error);
    });
}
