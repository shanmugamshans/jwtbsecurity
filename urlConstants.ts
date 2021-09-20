export class UrlConstants {

    public static get baseURL(): string { return "http://localhost:8080/simplelogin/"; }

    public static get AUTHENTICATE(): string { return this.baseURL + "authenticate"; }

    public static get LOGOUT(): string { return this.baseURL + "signout"; }

    public static get REGISTER(): string { return this.baseURL + "register"; }

    public static get GETALL_USER() : string  {return this.baseURL + "getAllUser"}

    public static get ADD_NEW_USER() : string  {return this.baseURL + "addNewUser"}

}