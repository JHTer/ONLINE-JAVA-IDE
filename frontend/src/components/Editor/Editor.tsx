import React, { useEffect, useState } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import { java } from '@codemirror/lang-java';

export interface EditorProps {
  code: string;
  onChange?: (value: string) => void;
  language?: 'java';
}

/**
 * Editor component powered by CodeMirror 6.
 * @param code Initial code string.
 * @param onChange Callback fired when content changes.
 * @param language Language mode (currently only 'java').
 */
const Editor: React.FC<EditorProps> = ({ code, onChange, language = 'java' }) => {
  const [value, setValue] = useState(code);

  // Keep internal state in sync if caller changes the prop.
  useEffect(() => {
    setValue(code);
  }, [code]);

  const handleChange = React.useCallback(
    (val: string) => {
      setValue(val);
      onChange?.(val);
    },
    [onChange]
  );

  return (
    <CodeMirror
      value={value}
      height="100%"
      theme="dark"
      extensions={language === 'java' ? [java()] : []}
      onChange={(v) => handleChange(v)}
      basicSetup={{
        lineNumbers: true,
        highlightActiveLineGutter: true,
        highlightActiveLine: true,
        autocompletion: true,
      }}
      style={{ background: '#1e1e1e' }}
    />
  );
};

export default Editor; 