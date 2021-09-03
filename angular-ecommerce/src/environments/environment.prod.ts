export const environment = {
  production: true,
  backendServerUrl: window["env"]["apiUrl"] || 'https://localhost:8443/api',//JavaScript is running in client's browser so to access server we must provide server URL accessible from browser
  // backendServerUrl: 'http://spring-boot-ecommerce:8080'
  debug: window["env"]["debug"] || false
};
