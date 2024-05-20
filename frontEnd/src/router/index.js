import { createRouter, createWebHistory } from 'vue-router';
import AdminDashboard from '../components/AdminDashboard.vue';
import UserDashboard from '../components/UserDashboard.vue';
import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import DataView from '../components/DataView.vue';
import UserManagement from '../components/UserManagement.vue'; // 修改导入的组件名

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage,
    },
    {
        path: '/admin',
        name: 'AdminDashboard',
        component: AdminDashboard,
        meta: {
            requiresAuth: true,
        },
    },
    {
        path: '/login',
        name: 'LoginPage',
        component: LoginPage,
    },
    {
        path: '/user',
        name: 'UserDashboard',
        component: UserDashboard,
        meta: {
            requiresAuth: true,
        },
    },
    {
        path: '/data-view', // 修改路径为小写
        name: 'DataView',
        component: DataView, // 修改为正确的组件
    },
    {
        path: '/user-management', // 修改路径为小写
        name: 'UserManagement',
        component: UserManagement, // 修改为正确的组件
        meta: {
            requiresAuth: true,
        }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
