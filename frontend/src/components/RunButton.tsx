import clsx from 'clsx';

interface RunButtonProps {
  onClick?: () => void;
  loading?: boolean;
}

const RunButton: React.FC<RunButtonProps> = ({ onClick, loading }) => {
  return (
    <button
      type="button"
      aria-label="Run code"
      title="Run Code (Ctrl+Enter)"
      onClick={onClick}
      disabled={loading}
      className={clsx(
        'px-4 py-1.5 text-sm font-medium text-white rounded hover:bg-blue-500 active:bg-blue-700',
        loading ? 'opacity-60 cursor-not-allowed' : 'bg-primary'
      )}
    >
      Run
    </button>
  );
};

export default RunButton; 