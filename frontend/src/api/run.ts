import client from './client';

export interface RunRequest {
  code: string;
  stdin?: string;
}

export interface RunResponse {
  stdout: string;
  stderr: string;
  status: 'SUCCESS' | 'COMPILE_ERROR' | 'RUNTIME_ERROR';
  execTimeMs: number;
}

export async function runCode(payload: RunRequest) {
  const { data } = await client.post<RunResponse>('/api/run', payload);
  return data;
} 