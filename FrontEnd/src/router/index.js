import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import DataView from '../components/DataView.vue';
import Registation from '../components/Registation.vue';
import UserManagement from '../components/UserManagement.vue';
import FormFill from '../components/FormFill.vue';
import FormView from '../components/FormView.vue';
import FormCreate from '../components/FormCreate.vue';

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage,
    },
    {
        path: '/login',
        name: 'LoginPage',
        component: LoginPage,
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
    },
    {
        path: '/regist',
        name: 'Registation',
        component: Registation,
    },
    {
        path: '/form-fill',
        name: 'FormFill',
        component: FormFill,
    },
    {
        path: '/form-view',
        name: 'FormView',
        component: FormView,
    },
    {
        path: '/form-create',
        name: 'FormCreate',
        component: FormCreate,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
