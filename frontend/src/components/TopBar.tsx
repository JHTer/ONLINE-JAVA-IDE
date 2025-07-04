interface TopBarProps {
  onRun: () => void;
  loading: boolean;
}

import RunButton from './RunButton';

const TopBar: React.FC<TopBarProps> = ({ onRun, loading }) => {
  return (
    <header className="bg-neutral-800 shadow flex items-center justify-between px-4 py-2">
      <h1 className="text-sm font-semibold">Java Online IDE</h1>
      <RunButton onClick={onRun} loading={loading} />
    </header>
  );
};

export default TopBar; 