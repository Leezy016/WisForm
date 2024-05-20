<!-- src/components/UserDashboard.vue -->

<template>
    <div class="user-dashboard">
        <h2>欢迎, 普通用户</h2>
        <DynamicForm />
    </div>
</template>

<script>
    import DynamicForm from '../components/DynamicForm.vue';

    export default {
        components: {
            DynamicForm
        }
    };
</script>

<style scoped>
    .user-dashboard {
        max-width: 600px;
        margin: auto;
    }
</style>