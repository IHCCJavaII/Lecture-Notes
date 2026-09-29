# Budget Analyzer

## Create AI Model

Using the `Model.txt` file, create a custom AI model using the Ollama platform.

```bash
# Navigate to where you downloaded the Modelfile (replace ~/Downloads if needed)
cd ~/Downloads

# Copy the Modelfile into the container
docker cp Modelfile ollama:/tmp/Modelfile

# Enter the container
docker exec -it ollama bash

# Create your specialized model
ollama create budget-coach -f tmp/Modelfile

# Verify it was created
ollama list

# Exit container
exit
```

## Test the Model

If your not already in the container, enter it again. And test your model passing the content of the `budget.csv` file as input.

```bash
ollama run budget-coach
```
