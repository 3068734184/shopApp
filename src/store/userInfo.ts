import { createPinia, defineStore } from "pinia";

interface userInfo {
    id: string | null,
    username: string | null,
    tel: string | null,
    token: string | null
}

export const useUserInfoStore = defineStore("userInfo", {
    state: (): userInfo => ({
        id: null,
        username: null,
        tel: null,
        token: null,
    }),
    actions: {
        setStoreUserInfo(id: string, username: string, tel: string, token: string) {
            this.id = id;
            this.username = username;
            this.tel = tel;
            this.token = token;
        },
        clearStoreUserInfo() {
            this.id = null;
            this.username = null;
            this.tel = null;
            this.token = null;
        }
    }
});

const pinia = createPinia();
export default pinia;