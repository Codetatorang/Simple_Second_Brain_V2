export interface User {
    id: number;
    userName: string;
    userPassword: string;
    accName: string;
    email: string;
}

export interface UserLogin {
    userName: string;
    userPassword: string;
}
