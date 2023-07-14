import { UserConfigFn } from 'vite';
import { overrideVaadinConfig } from './vite.generated';
import pkg from 'glob';
const { sync: sync3 } = pkg;
const customConfig: UserConfigFn = (env) => ({
  // Here you can add custom Vite parameters
  // https://vitejs.dev/config/
});

export default overrideVaadinConfig(customConfig);
