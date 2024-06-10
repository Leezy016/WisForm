import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import Registation from '../components/Registation.vue';
import UserManagement from '../components/UserManagement.vue';
import FormFill from '../components/FormFill.vue';
import FormView from '../components/FormView.vue';
import FormCreate from '../components/FormCreate.vue';
import FormFillDetail from '../components/FormFillDetail.vue';
import FormViewDetail from '../components/FormViewDetail.vue';
import FormChange from '../components/FormChange.vue';

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
        path: '/form-view/:num',
        name: 'FormView',
        component: FormView,
        props: true
    },
    {
        path: '/form-create',
        name: 'FormCreate',
        component: FormCreate,
    },
    {
        path: '/form-fill/detail/:title',
        name: 'FormFillDetail',
        component: FormFillDetail,
        props: true
    },
    {
        path: '/form-view/detail/:title',
        name: 'FormViewDetail',
        component: FormViewDetail,
        props: true
    },
    {
        path: '/form-change/:id',
        name: 'FormChange',
        component: FormChange,
        props: true
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
