import requests
import json

url = "http://146.169.193.116/Party/?format=json"
response = requests.get(url)
if response.status_code != 200:
    print('error {}'.format(response.status_code))
else:
    data = json.loads(response.text)
    print(json.dumps(data, indent=4))

data = {'Name': 'lol',
        'Hostname':'Alex',
        'Location' : 'bedford'}

urlend = "http://146.169.193.116/Party/"


r = requests.post(url = urlend, data = data)
print(r.status_code)