export default class SysProperties
{
  "protocol" : "http";
  "host" : "localhost";
  "port" : "883";
  "forms-api-context" : "/forms/api/";

  getProperty(name:string){
    return this[name];
  }
}