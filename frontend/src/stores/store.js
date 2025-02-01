import { createStore } from 'vuex';

export default createStore({
    state: {
        userRole: localStorage.getItem('userRole') || "",
        username: localStorage.getItem('username') || "",
        id: localStorage.getItem('id') || null,
    },
    getters: {
        userRole: (state) => state.userRole,
        username: (state) => state.username,
        id: (state) => state.id,
    },
    mutations: {
        setUserRole(state, role) {
            state.userRole = role;
            localStorage.setItem('userRole', role); // เก็บค่าลง Local Storage
        },
        setUsername(state, username) {
            state.username = username;
            localStorage.setItem('username', username); // เก็บค่าลง Local Storage
        },
        setId(state, id) {
            state.id = id;
            localStorage.setItem('id', id); // เก็บค่าลง Local Storage
        },
        clearUserData(state) {
            state.userRole = "";
            state.username = "";
            state.id = null;
            localStorage.removeItem('userRole');
            localStorage.removeItem('username');
            localStorage.removeItem('id');
        },
    },
    actions: {
        updateUserRole({ commit }, role) {
            commit("setUserRole", role);
        },
        updateUsername({ commit }, username) {
            commit("setUsername", username);
        },
        updateId({ commit }, id) {
            commit("setId", id);
        },
        clearUserData({ commit }) {
            commit("clearUserData");
        },
    },
});
