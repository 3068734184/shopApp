(function (a, h, g, f, e, d, c, b) {
  b = function () {
    d = h.createElement(g);
    c = h.getElementsByTagName(g)[0];
    d.src = e;
    d.charset = "utf-8";
    d.async = 1;
    c.parentNode.insertBefore(d, c)
  };
  a["SeniverseWeatherWidgetObject"] = f;
  a[f] || (a[f] = function () { (a[f].q = a[f].q || []).push(arguments) });
  a[f].l = +new Date();
  if (a.attachEvent) {
    a.attachEvent("onload", b)
  }
  else {
    a.addEventListener("load", b, false)
  }
}(
  window, document,
  "script", "SeniverseWeatherWidget",
  "//cdn.sencdn.com/widget2/static/js/bundle.js?t=" + parseInt((new Date().getTime() / 100000000).toString(), 10)));
window.SeniverseWeatherWidget('show', {
  flavor: "slim",
  location: "WX4FBXXFKE4F",
  geolocation: true,
  language: "zh-Hans",
  unit: "c",
  theme: "auto",
  token: "9dee35d9-7924-40e5-a7f7-c8c8c65bc509",
  hover: "always",
  container: "tp-weather-widget"
})


export function weatherWidget() {

}